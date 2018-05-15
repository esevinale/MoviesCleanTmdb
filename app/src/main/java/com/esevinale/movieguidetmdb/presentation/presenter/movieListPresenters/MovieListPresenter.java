package com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters;

import android.support.annotation.NonNull;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.domain.entity.Movie;
import com.esevinale.movieguidetmdb.domain.interactor.DefaultSubscriber;
import com.esevinale.movieguidetmdb.domain.interactor.UseCase;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelMapper;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.presenter.Presenter;
import com.esevinale.movieguidetmdb.presentation.view.MovieListView;
import com.esevinale.movieguidetmdb.presentation.view.utils.EspressoIdlingResource;

import java.util.List;

public abstract class MovieListPresenter implements Presenter {

    private MovieListView mMovieListView;

    private final MovieModelMapper mMovieModelDataMapper;
    private final UseCase mGetMovieList;

    private boolean mIsInLoading;

    private final static int FIRST_PAGE = 1;

    MovieListPresenter(UseCase getMovieList, MovieModelMapper movieModelDataMapper) {
        this.mGetMovieList = getMovieList;
        this.mMovieModelDataMapper = movieModelDataMapper;
    }

    public void setView(@NonNull MovieListView view) {
        this.mMovieListView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.mGetMovieList.dispose();
        this.mMovieListView = null;
    }

    public void initialize() {
        this.loadMovieList(FIRST_PAGE, ProgressType.ListProgress);
    }

    private void loadMovieList(int page, ProgressType progressType) {
        if (mIsInLoading) return;
        this.hideAllProgress();
        mIsInLoading = true;
        this.showProgress(progressType);
        this.getMovieList(page, progressType);
    }

    public void loadNextMovies(int page) {
        loadMovieList(page, ProgressType.Paging);
    }

    public void loadRefresh() {
        loadMovieList(FIRST_PAGE, ProgressType.Refreshing);
    }

    public void onMovieClicked(MovieModel movieModel) {
        this.mMovieListView.viewMovie(movieModel);
    }

    private void showProgress(ProgressType progressType) {
        switch (progressType) {
            case Refreshing:
                mMovieListView.showRefreshing();
                break;
            case ListProgress:
                mMovieListView.showLoading();
                break;
        }
    }

    private void hideProgress(ProgressType progressType) {
        switch (progressType) {
            case Refreshing:
                mMovieListView.hideRefreshing();
                break;
            case ListProgress:
                mMovieListView.hideLoading();
                break;
        }
        mIsInLoading = false;
    }

    private void hideAllProgress() {
        mMovieListView.hideLoading();
        mMovieListView.hideRefreshing();
    }

    private void showErrorMessage() {
        this.mMovieListView.showError(mMovieListView.context().getString(R.string.default_error));
    }

    private void showMovieListInView(List<Movie> movieList) {
        final List<MovieModel> movieModels =
                this.mMovieModelDataMapper.transform(movieList);
        this.mMovieListView.renderMovieList(movieModels);
    }

    private void addMovieListInView(List<Movie> movieList) {
        final List<MovieModel> movieModels =
                this.mMovieModelDataMapper.transform(movieList);
        this.mMovieListView.addMoviesToList(movieModels);
    }

    @SuppressWarnings("unchecked")
    private void getMovieList(int page, ProgressType progressType) {
        EspressoIdlingResource.increment();
        this.mGetMovieList.execute(new MovieListSubscriber(progressType), page);
    }

    private final class MovieListSubscriber extends DefaultSubscriber<List<Movie>> {

        private ProgressType progressType;

        MovieListSubscriber(ProgressType progressType) {
            super();
            this.progressType = progressType;
        }

        @Override
        public void onComplete() {
            MovieListPresenter.this.hideProgress(progressType);
            EspressoIdlingResource.decrement();
        }

        @Override
        public void onError(Throwable e) {
            MovieListPresenter.this.hideProgress(progressType);
            MovieListPresenter.this.showErrorMessage();
        }

        @Override
        public void onNext(List<Movie> movies) {
            if (progressType == ProgressType.Paging)
                MovieListPresenter.this.addMovieListInView(movies);
            else
                MovieListPresenter.this.showMovieListInView(movies);
        }
    }

    enum ProgressType {
        Refreshing, ListProgress, Paging
    }
}
