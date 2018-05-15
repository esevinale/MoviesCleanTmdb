package com.esevinale.movieguidetmdb.presentation.presenter;

import android.content.Context;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.data.net.ApiConstants;
import com.esevinale.movieguidetmdb.domain.entity.Trailer;
import com.esevinale.movieguidetmdb.domain.entity.details.Cast;
import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetails;
import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetailsDomainModel;
import com.esevinale.movieguidetmdb.domain.interactor.DefaultSubscriber;
import com.esevinale.movieguidetmdb.domain.interactor.GetMovieDetails;
import com.esevinale.movieguidetmdb.presentation.mapper.CastModelMapper;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieDetailsModelMapper;
import com.esevinale.movieguidetmdb.presentation.mapper.TrailerModelMapper;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.view.MovieDetailsView;

import org.junit.After;
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

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

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
    private TrailerModelMapper trailerModelDataMapper;
    @Mock
    private MovieDetailsModelMapper movieDetailsDataMapper;
    @Mock
    private CastModelMapper castModelMapper;
    @Mock
    private GetMovieDetails getMovieDetails;
    @Captor
    private ArgumentCaptor<DefaultSubscriber<MovieDetailsDomainModel>> detailsSubscriberCaptor;

    @Before
    public void setUp() {
        presenter = new MovieDetailsPresenter(trailerModelDataMapper, movieDetailsDataMapper, getMovieDetails,
                castModelMapper);
        presenter.setView(movieDetailsView);

        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void testCreated() {
        assertNotNull(presenter);
    }

    @Test
    public void destroy() {
        presenter.destroy();
        verify(getMovieDetails).dispose();
    }

    @Test
    public void initialize() {
        presenter.initialize(getMovieModel());

        verify(movieDetailsView).showLoading();
        verify(movieDetailsView).showMovieImages(ApiConstants.BACK_TMDB_URL + "test", ApiConstants.POSTER_TMDB_URL + "test");

        verify(getMovieDetails).execute(detailsSubscriberCaptor.capture(), any(Integer.class));

        MovieDetailsDomainModel domainModel = getDomainModel();
        detailsSubscriberCaptor.getValue().onNext(domainModel);

        verify(movieDetailsView).showMovieDetails(movieDetailsDataMapper.transformDetails(domainModel.getMovieDetails()));
        verify(movieDetailsView).showTrailers(trailerModelDataMapper.transform(domainModel.getTrailers()));
        verify(movieDetailsView).showCast(castModelMapper.transform(domainModel.getCast()));
        detailsSubscriberCaptor.getValue().onComplete();
        verify(movieDetailsView).hideLoading();
    }

    @Test
    public void loadEmptyTrailers() {
        presenter.initialize(getMovieModel());

        verify(movieDetailsView).showLoading();
        verify(movieDetailsView).showMovieImages(ApiConstants.BACK_TMDB_URL + "test", ApiConstants.POSTER_TMDB_URL + "test");

        verify(getMovieDetails).execute(detailsSubscriberCaptor.capture(), any(Integer.class));

        MovieDetailsDomainModel domainModel = getEmptyModel();
        detailsSubscriberCaptor.getValue().onNext(domainModel);

        verify(movieDetailsView).showMovieDetails(movieDetailsDataMapper.transformDetails(domainModel.getMovieDetails()));

        detailsSubscriberCaptor.getValue().onComplete();
        verify(movieDetailsView).hideLoading();

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

        verify(movieDetailsView).showError("TestError");
    }

    @Test
    public void onTrailerClicked() {
        presenter.onTrailerClicked("url");

        verify(movieDetailsView).startTrailerIntent("url");
    }

    private MovieDetailsDomainModel getDomainModel() {
        MovieDetailsDomainModel domainModel = new MovieDetailsDomainModel();
        List<Cast> cast = new ArrayList<>();
        cast.add(new Cast("char", 1, 1, "name", "path"));
        domainModel.setCast(cast);
        List<Trailer> trailers = new ArrayList<>();
        trailers.add(new Trailer("id", "key", "name", "site"));
        domainModel.setTrailers(trailers);
        domainModel.setMovieDetails(new MovieDetails());
        return domainModel;
    }

    private MovieDetailsDomainModel getEmptyModel() {
        MovieDetailsDomainModel domainModel = new MovieDetailsDomainModel();
        domainModel.setCast(new ArrayList<>());
        domainModel.setTrailers(new ArrayList<>());
        domainModel.setMovieDetails(new MovieDetails());
        return domainModel;
    }

    private MovieModel getMovieModel() {
        return new MovieModel(1, null, "test", "test");
    }

    @After
    public void tearDown() {
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();
    }
}