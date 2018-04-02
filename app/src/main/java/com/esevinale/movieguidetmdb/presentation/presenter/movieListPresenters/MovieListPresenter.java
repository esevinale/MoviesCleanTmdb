package com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters;

import android.support.annotation.NonNull;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.domain.entity.Movie;
import com.esevinale.movieguidetmdb.domain.interactor.DefaultSubscriber;
import com.esevinale.movieguidetmdb.domain.interactor.UseCase;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelDataMapper;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.presenter.Presenter;
import com.esevinale.movieguidetmdb.presentation.view.MovieListView;

import java.util.List;

public abstract class MovieListPresenter implements Presenter {

    private MovieListView movieListView;

    private final MovieModelDataMapper movieModelDataMapper;
    private final UseCase getMovieList;

    private boolean mIsInLoading;

    private final int FIRST_PAGE = 1;

    MovieListPresenter(UseCase getMovieList, MovieModelDataMapper movieModelDataMapper) {
        this.getMovieList = getMovieList;
        this.movieModelDataMapper = movieModelDataMapper;
    }

    public void setView(@NonNull MovieListView view) {
        this.movieListView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getMovieList.dispose();
        this.movieListView = null;
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
        this.movieListView.viewMovie(movieModel);
    }

    private void showProgress(ProgressType progressType) {
        switch (progressType) {
            case Refreshing:
                movieListView.showRefreshing();
                break;
            case ListProgress:
                movieListView.showLoading();
                break;
        }
    }

    private void hideProgress(ProgressType progressType) {
        mIsInLoading = false;
        switch (progressType) {
            case Refreshing:
                movieListView.hideRefreshing();
                break;
            case ListProgress:
                movieListView.hideLoading();
                break;
        }
    }

    private void hideAllProgress() {
        movieListView.hideLoading();
        movieListView.hideRefreshing();
    }

    private void showErrorMessage() {
        this.movieListView.showError(movieListView.context().getString(R.string.default_error));
    }

    private void showMovieListInView(List<Movie> movieList) {
        final List<MovieModel> movieModels =
                this.movieModelDataMapper.transform(movieList);
        this.movieListView.renderMovieList(movieModels);
    }

    private void addMovieListInView(List<Movie> movieList) {
        final List<MovieModel> movieModels =
                this.movieModelDataMapper.transform(movieList);
        this.movieListView.addMoviesToList(movieModels);
    }

    @SuppressWarnings("unchecked")
    private void getMovieList(int page, ProgressType progressType) {
        this.getMovieList.execute(new MovieListSubscriber(progressType), page);
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
