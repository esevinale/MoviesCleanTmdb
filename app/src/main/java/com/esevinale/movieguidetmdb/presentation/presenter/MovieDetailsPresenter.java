package com.esevinale.movieguidetmdb.presentation.presenter;

import android.support.annotation.NonNull;

import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.data.net.ApiConstants;
import com.esevinale.movieguidetmdb.domain.entity.Trailer;
import com.esevinale.movieguidetmdb.domain.entity.details.Cast;
import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetails;
import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetailsDomainModel;
import com.esevinale.movieguidetmdb.domain.interactor.DefaultSubscriber;
import com.esevinale.movieguidetmdb.domain.interactor.GetMovieDetails;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.CastModelMapper;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieDetailsModelMapper;
import com.esevinale.movieguidetmdb.presentation.mapper.TrailerModelMapper;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.model.TrailerModel;
import com.esevinale.movieguidetmdb.presentation.model.details.CastModel;
import com.esevinale.movieguidetmdb.presentation.model.details.MovieDetailsModel;
import com.esevinale.movieguidetmdb.presentation.view.views.MovieDetailsView;
import com.esevinale.movieguidetmdb.presentation.view.utils.EspressoIdlingResource;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class MovieDetailsPresenter implements Presenter {

    private MovieDetailsView mMovieDetailsView;

    private final TrailerModelMapper mTrailerModelDataMapper;
    private final MovieDetailsModelMapper mMovieDetailsDataMapper;
    private final GetMovieDetails mGetNewMovieDetails;
    private final CastModelMapper mCreditsModelMapper;

    @Inject
    public MovieDetailsPresenter(TrailerModelMapper trailerModelDataMapper, MovieDetailsModelMapper movieDetailsDataMapper, GetMovieDetails getNewMovieDetails, CastModelMapper mCreditsModelMapper) {
        this.mTrailerModelDataMapper = trailerModelDataMapper;
        this.mMovieDetailsDataMapper = movieDetailsDataMapper;
        this.mGetNewMovieDetails = getNewMovieDetails;
        this.mCreditsModelMapper = mCreditsModelMapper;
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
        this.mGetNewMovieDetails.dispose();
        this.mMovieDetailsView = null;
    }

    public void initialize(MovieModel movie) {
        this.showViewLoading();
        this.mMovieDetailsView.showMovieImages(ApiConstants.BACK_TMDB_URL + movie.getBackdropPath(), ApiConstants.POSTER_TMDB_URL + movie.getPosterPath());
        getNewMovieDetails(movie.getId());
    }

    public void onTrailerClicked(String url) {
        this.mMovieDetailsView.startTrailerIntent(url);
    }

    private void getNewMovieDetails(int id) {
        EspressoIdlingResource.increment();
        this.mGetNewMovieDetails.execute(new MovieDetailsSubscriber(), id);
    }

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
                this.mTrailerModelDataMapper.transform(trailers);
        this.mMovieDetailsView.showTrailers(trailerList);
    }

    private void showDetailsInView(MovieDetails movieDetails) {
        final MovieDetailsModel movieDetailsModel =
                this.mMovieDetailsDataMapper.transformDetails(movieDetails);
        this.mMovieDetailsView.showMovieDetails(movieDetailsModel);

    }

    private void showCastInView(List<Cast> cast) {
        if (cast.size() == 0) return;
        final List<CastModel> castList =
                this.mCreditsModelMapper.transform(cast);
        this.mMovieDetailsView.showCast(castList);
    }

    private final class MovieDetailsSubscriber extends DefaultSubscriber<MovieDetailsDomainModel> {

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
        public void onNext(MovieDetailsDomainModel details) {
            MovieDetailsPresenter.this.showDetailsInView(details.getMovieDetails());
            MovieDetailsPresenter.this.showTrailersInView(details.getTrailers());
            MovieDetailsPresenter.this.showCastInView(details.getCast());
        }
    }
}
