package com.esevinale.movieguidetmdb.presentation.presenter;

import android.content.Context;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.data.net.ApiConstants;
import com.esevinale.movieguidetmdb.domain.entity.Trailer;
import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetails;
import com.esevinale.movieguidetmdb.domain.interactor.DefaultSubscriber;
import com.esevinale.movieguidetmdb.domain.interactor.GetMovieDetails;
import com.esevinale.movieguidetmdb.domain.interactor.GetMovieImages;
import com.esevinale.movieguidetmdb.domain.interactor.GetMovieTrailers;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieDetailsDataMapper;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieImagesDataMapper;
import com.esevinale.movieguidetmdb.presentation.mapper.TrailerModelDataMapper;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.view.MovieDetailsView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class MovieDetailsPresenterTest {

    private MovieDetailsPresenter presenter;

    @Mock
    private Context context;
    @Mock
    private MovieDetailsView movieDetailsView;
    @Mock
    private GetMovieTrailers getMovieTrailers;
    @Mock
    private GetMovieImages getMovieImages;
    @Mock
    private GetMovieDetails getMovieDetails;
    @Mock
    private TrailerModelDataMapper trailerModelDataMapper;
    @Mock
    private MovieImagesDataMapper movieImagesDataMapper;
    @Mock
    private MovieDetailsDataMapper movieDetailsDataMapper;
    @Captor
    private ArgumentCaptor<DefaultSubscriber<List<Trailer>>> trailersSubscriberCaptor;
    @Captor
    private ArgumentCaptor<DefaultSubscriber<MovieDetails>> detailsSubscriberCaptor;

    @Before
    public void setUp() {
        presenter = new MovieDetailsPresenter(getMovieTrailers, getMovieImages, getMovieDetails,
                trailerModelDataMapper, movieImagesDataMapper, movieDetailsDataMapper);
        presenter.setView(movieDetailsView);
    }

    @Test
    public void testCreated() {
        assertNotNull(presenter);
    }

    @Test
    public void destroy() {
        presenter.destroy();
        verify(getMovieTrailers).dispose();
        verify(getMovieImages).dispose();
        verify(getMovieDetails).dispose();
    }

    @Test
    public void initialize() {
        presenter.initialize(getMovieModel());

        verify(movieDetailsView).showLoading();
        verify(movieDetailsView).showMovieImages(ApiConstants.BACK_TMDB_URL + "test", ApiConstants.POSTER_TMDB_URL + "test");

        verify(getMovieDetails).execute(detailsSubscriberCaptor.capture(), any(Integer.class));

        MovieDetails md = getMovieDetails();
        detailsSubscriberCaptor.getValue().onNext(md);

        verify(movieDetailsView).showMovieDetails(movieDetailsDataMapper.transformDetails(md));

        detailsSubscriberCaptor.getValue().onComplete();
        verify(movieDetailsView).hideLoading();

        verify(getMovieTrailers).execute(trailersSubscriberCaptor.capture(), any(Integer.class));
        List<Trailer> trailers = getTrailerList();
        trailersSubscriberCaptor.getValue().onNext(trailers);
        verify(movieDetailsView).showTrailers(trailerModelDataMapper.transform(trailers));
    }

    @Test
    public void loadEmptyTrailers() {
        presenter.initialize(getMovieModel());

        verify(movieDetailsView).showLoading();
        verify(movieDetailsView).showMovieImages(ApiConstants.BACK_TMDB_URL + "test", ApiConstants.POSTER_TMDB_URL + "test");

        verify(getMovieDetails).execute(detailsSubscriberCaptor.capture(), any(Integer.class));

        MovieDetails md = getMovieDetails();
        detailsSubscriberCaptor.getValue().onNext(md);

        verify(movieDetailsView).showMovieDetails(movieDetailsDataMapper.transformDetails(md));

        detailsSubscriberCaptor.getValue().onComplete();
        verify(movieDetailsView).hideLoading();

        verify(getMovieTrailers).execute(trailersSubscriberCaptor.capture(), any(Integer.class));
        List<Trailer> trailers = new ArrayList<>();
        trailersSubscriberCaptor.getValue().onNext(trailers);
        verifyNoMoreInteractions(movieDetailsView);
    }

    @Test
    public void loadWithError() {
        given(movieDetailsView.context()).willReturn(context);
        when(context.getString(R.string.default_error)).thenReturn("TestError");

        presenter.initialize(getMovieModel());

        verify(movieDetailsView).showLoading();
        verify(movieDetailsView).showMovieImages(ApiConstants.BACK_TMDB_URL + "test", ApiConstants.POSTER_TMDB_URL + "test");

        verify(getMovieDetails).execute(detailsSubscriberCaptor.capture(), any(Integer.class));
        detailsSubscriberCaptor.getValue().onError(new IOException());
        verify(movieDetailsView).hideLoading();
        verify(movieDetailsView).showError("TestError");

        verify(getMovieTrailers).execute(trailersSubscriberCaptor.capture(), any(Integer.class));
        trailersSubscriberCaptor.getValue().onError(new IOException());
        verify(movieDetailsView, times(2)).showError("TestError");
    }

    @Test
    public void onTrailerClicked() {
        presenter.onTrailerClicked("url");

        verify(movieDetailsView).startTrailerIntent("url");
    }

    private MovieModel getMovieModel() {
        return new MovieModel(1, null, "test", "test");
    }

    private MovieDetails getMovieDetails() {
        return new MovieDetails();
    }

    private List<Trailer> getTrailerList() {
        List<Trailer> trailers = new ArrayList<>();
        trailers.add(new Trailer("id", "key", "name", "site"));
        return trailers;
    }
}