package com.esevinale.movieguidetmdb.presentation.presenter;

import android.support.annotation.NonNull;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.data.net.ApiConstants;
import com.esevinale.movieguidetmdb.domain.entity.Trailer;
import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetails;
import com.esevinale.movieguidetmdb.domain.interactor.DefaultSubscriber;
import com.esevinale.movieguidetmdb.domain.interactor.GetMovieDetails;
import com.esevinale.movieguidetmdb.domain.interactor.GetMovieImages;
import com.esevinale.movieguidetmdb.domain.interactor.GetMovieTrailers;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieDetailsDataMapper;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieImagesDataMapper;
import com.esevinale.movieguidetmdb.presentation.mapper.TrailerModelDataMapper;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.model.TrailerModel;
import com.esevinale.movieguidetmdb.presentation.model.details.MovieDetailsModel;
import com.esevinale.movieguidetmdb.presentation.view.MovieDetailsView;
import com.esevinale.movieguidetmdb.presentation.view.utils.EspressoIdlingResource;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class MovieDetailsPresenter implements Presenter {

    private MovieDetailsView mMovieDetailsView;

    private final GetMovieTrailers getMovieTrailers;
    private final GetMovieImages getMovieImages;
    private final GetMovieDetails getMovieDetails;
    private final TrailerModelDataMapper trailerModelDataMapper;
    private final MovieImagesDataMapper movieImagesDataMapper;
    private final MovieDetailsDataMapper movieDetailsDataMapper;

    @Inject
    MovieDetailsPresenter(GetMovieTrailers getMovieTrailers, GetMovieImages getMovieImages, GetMovieDetails getMovieDetails, TrailerModelDataMapper trailerModelDataMapper,
                          MovieImagesDataMapper movieImagesDataMapper, MovieDetailsDataMapper movieDetailsDataMapper) {
        this.getMovieTrailers = getMovieTrailers;
        this.getMovieImages = getMovieImages;
        this.getMovieDetails = getMovieDetails;
        this.trailerModelDataMapper = trailerModelDataMapper;
        this.movieImagesDataMapper = movieImagesDataMapper;
        this.movieDetailsDataMapper = movieDetailsDataMapper;
    }

    public void setView(@NonNull MovieDetailsView movieDetailsView) {
        this.mMovieDetailsView = movieDetailsView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getMovieTrailers.dispose();
        this.getMovieImages.dispose();
        this.getMovieDetails.dispose();
        this.mMovieDetailsView = null;
    }

    public void initialize(MovieModel movie) {
        this.showViewLoading();
        this.mMovieDetailsView.showMovieImages(ApiConstants.BACK_TMDB_URL + movie.getBackdropPath(), ApiConstants.POSTER_TMDB_URL + movie.getPosterPath());
        getMovieDetails(movie.getId());
//        getMovieImages(movieID);
        getMovieTrailers(movie.getId());
    }

    public void onTrailerClicked(String url) {
        this.mMovieDetailsView.startTrailerIntent(url);
    }

    private void getMovieTrailers(int id) {
        EspressoIdlingResource.increment();
        this.getMovieTrailers.execute(new MovieTrailersSubscriber(), id);
    }

    private void getMovieDetails(int id) {
        EspressoIdlingResource.increment();
        this.getMovieDetails.execute(new MovieDetailsSubscriber(), id);
    }

//    private void getMovieImages(int id) {
//        this.getMovieImages.execute(new MovieImagesSubscriber(), id);
//    }

    private void showViewLoading() {
        this.mMovieDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.mMovieDetailsView.hideLoading();
    }

    private void showErrorMessage() {
        this.mMovieDetailsView.showError(this.mMovieDetailsView.context().getString(R.string.default_error));
    }

    private void showTrailersInView(List<Trailer> trailers) {
        if (trailers.size() == 0) return;
        final List<TrailerModel> trailerList =
                this.trailerModelDataMapper.transform(trailers);
        this.mMovieDetailsView.showTrailers(trailerList);
    }
//
//    private void showImagesInView(Images images) {
//        final ImagesModel imagesModel =
//                this.movieImagesDataMapper.transformImages(images);
//        this.movieDetailsView.showMovieBackdrop(imagesModel);
//    }

    private void showDetailsInView(MovieDetails movieDetails) {
        final MovieDetailsModel movieDetailsModel =
                this.movieDetailsDataMapper.transformDetails(movieDetails);
        this.mMovieDetailsView.showMovieDetails(movieDetailsModel);

    }

    private final class MovieTrailersSubscriber extends DefaultSubscriber<List<Trailer>> {

        @Override
        public void onComplete() {
            EspressoIdlingResource.decrement();
        }

        @Override
        public void onError(Throwable e) {
            MovieDetailsPresenter.this.showErrorMessage();
        }

        @Override
        public void onNext(List<Trailer> trailers) {
            MovieDetailsPresenter.this.showTrailersInView(trailers);
        }
    }

    private final class MovieDetailsSubscriber extends DefaultSubscriber<MovieDetails> {

        @Override
        public void onComplete() {
            MovieDetailsPresenter.this.hideViewLoading();
            EspressoIdlingResource.decrement();
        }

        @Override
        public void onError(Throwable e) {
            MovieDetailsPresenter.this.hideViewLoading();
            MovieDetailsPresenter.this.showErrorMessage();
        }

        @Override
        public void onNext(MovieDetails details) {
            MovieDetailsPresenter.this.showDetailsInView(details);
        }
    }

//    private final class MovieImagesSubscriber extends DefaultSubscriber<Images> {
//
//        @Override public void onComplete() {
//        }
//
//        @Override public void onError(Throwable e) {
//            MovieDetailsPresenter.this.showErrorMessage();
//        }
//
//        @Override public void onNext(Images images) {
//            MovieDetailsPresenter.this.showImagesInView(images);
//        }
//    }
}
