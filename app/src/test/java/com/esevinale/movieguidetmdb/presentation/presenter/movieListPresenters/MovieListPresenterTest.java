package com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters;

import android.content.Context;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.domain.entity.Movie;
import com.esevinale.movieguidetmdb.domain.interactor.DefaultSubscriber;
import com.esevinale.movieguidetmdb.domain.interactor.GetNowPlayingMovieList;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelMapper;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.view.MovieListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
public class MovieListPresenterTest {

    private MovieListPresenter presenter;

    @Mock
    private Context context;
    @Mock
    private MovieListView movieListView;
    @Mock
    private MovieModelMapper movieModelDataMapper;
    @Mock
    private GetNowPlayingMovieList getMovieList;
    @Captor
    private ArgumentCaptor<DefaultSubscriber<List<Movie>>> moviesSubscriberCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MovieNowPlayingPresenter(getMovieList, movieModelDataMapper);
        presenter.setView(movieListView);

        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void testCreated() {
        assertNotNull(presenter);
    }

    @Test
    public void initialize() {
        presenter.initialize();

        verify(movieListView).hideLoading();
        verify(movieListView).hideRefreshing();
        verify(movieListView).showLoading();

        verify(getMovieList).execute(moviesSubscriberCaptor.capture(), any(Integer.class));

        moviesSubscriberCaptor.getValue().onNext(emptyMovies());
        verify(movieListView).renderMovieList(emptyMovieModels());

        moviesSubscriberCaptor.getValue().onComplete();
        verify(movieListView, times(2)).hideLoading();
    }

    private List<MovieModel> emptyMovieModels() {
        return new ArrayList<>();
    }

    private List<Movie> emptyMovies() {
        return new ArrayList<>();
    }

    @Test
    public void loadWithError() {
        given(movieListView.context()).willReturn(context);
        when(context.getString(R.string.default_error)).thenReturn("TestError");
        presenter.initialize();

        verify(movieListView).hideLoading();
        verify(movieListView).hideRefreshing();
        verify(movieListView).showLoading();

        verify(getMovieList).execute(moviesSubscriberCaptor.capture(), any(Integer.class));

        moviesSubscriberCaptor.getValue().onError(new IOException());
        verify(movieListView, times(2)).hideLoading();
        verify(movieListView).showError("TestError");
    }

    @Test
    public void loadNextMovies() {
        presenter.loadNextMovies(2);

        verify(movieListView).hideLoading();
        verify(movieListView).hideRefreshing();
        verify(getMovieList).execute(moviesSubscriberCaptor.capture(), any(Integer.class));

        moviesSubscriberCaptor.getValue().onNext(emptyMovies());
        verify(movieListView).addMoviesToList(emptyMovieModels());

        moviesSubscriberCaptor.getValue().onComplete();
        verifyNoMoreInteractions(movieListView);
    }

    @Test
    public void loadRefresh() {
        presenter.loadRefresh();

        verify(movieListView).hideLoading();
        verify(movieListView).hideRefreshing();
        verify(movieListView).showRefreshing();

        verify(getMovieList).execute(moviesSubscriberCaptor.capture(), any(Integer.class));

        moviesSubscriberCaptor.getValue().onNext(emptyMovies());
        verify(movieListView).renderMovieList(emptyMovieModels());

        moviesSubscriberCaptor.getValue().onComplete();
        verify(movieListView, times(2)).hideRefreshing();
    }

    @Test
    public void onMovieClicked() {
        MovieModel movieModel = new MovieModel(0 , null, null, null);
        presenter.onMovieClicked(movieModel);

        verify(movieListView).viewMovie(movieModel);
    }

    @Test
    public void onDestroy() {
        presenter.destroy();
        verify(getMovieList).dispose();
    }

    @After
    public void tearDown() {
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();
    }
}