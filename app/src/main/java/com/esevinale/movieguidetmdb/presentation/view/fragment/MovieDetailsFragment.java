package com.esevinale.movieguidetmdb.presentation.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.data.net.ApiConstants;
import com.esevinale.movieguidetmdb.domain.entity.details.ProductionCompany;
import com.esevinale.movieguidetmdb.presentation.AndroidApp;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.model.TrailerModel;
import com.esevinale.movieguidetmdb.presentation.model.details.MovieDetailsModel;
import com.esevinale.movieguidetmdb.presentation.model.details.ProductionCompanyModel;
import com.esevinale.movieguidetmdb.presentation.model.image.BackdropModel;
import com.esevinale.movieguidetmdb.presentation.model.image.ImagesModel;
import com.esevinale.movieguidetmdb.presentation.model.image.PosterModel;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieDetailsPresenter;
import com.esevinale.movieguidetmdb.presentation.view.MovieDetailsView;
import com.esevinale.movieguidetmdb.presentation.view.utils.Constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

public class MovieDetailsFragment extends BaseFragment implements MovieDetailsView {

    @BindView(R.id.movie_name)
    TextView movieName;
    @BindView(R.id.sites)
    TextView movieSite;
    @BindView(R.id.companies)
    TextView movieCompanies;
    @BindView(R.id.movie_rating)
    TextView movieRate;
    @BindView(R.id.movie_overview_content)
    TextView movieOverview;
    @BindView(R.id.movie_poster)
    ImageView moviePoster;
    @BindView(R.id.collapsedToolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.trailers_section)
    LinearLayout trailerSection;
    @BindView(R.id.ll_trailers)
    LinearLayout trailersll;

    @Inject
    MovieDetailsPresenter movieDetailsPresenter;

    public static MovieDetailsFragment getInstance(int movieId) {
        Bundle args = new Bundle();
        args.putInt(Constants.MOVIE_ID, movieId);
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(args);
        return movieDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieDetailsPresenter.setView(this);
        mProgressBar = getBaseActivity().getProgressBar();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle extras = getArguments();
        if (extras != null && extras.containsKey(Constants.MOVIE_ID)) {
            movieDetailsPresenter.initialize(getArguments().getInt(Constants.MOVIE_ID));

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        movieDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        movieDetailsPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieDetailsPresenter.destroy();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_movie_details;
    }

    @Override
    public void showMovieDetails(MovieDetailsModel movieModel) {
        collapsingToolbar.setTitle(String.format(getString(R.string.movie_details_toolbar), movieModel.getTitle()));
        movieSite.setText(movieModel.getHomepage());
        StringBuilder sb = new StringBuilder();
        Iterator<ProductionCompanyModel> iterator = movieModel.getProductionCompanies().iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getName());
            if (iterator.hasNext())
                sb.append(", ");
        }
        movieModel.getta
        movieCompanies.setText(sb.toString());
        movieName.setText(String.format(getString(R.string.movie), movieModel.getTitle(), movieModel.getReleaseDate().substring(0, 4)));
        movieRate.setText(String.format(getString(R.string.movie_rating), String.valueOf(movieModel.getVoteAverage())));
        movieOverview.setText(movieModel.getOverview());
    }

    @Override
    public void showMovieBackdrop(String path) {
        Glide
                .with(context())
                .load(path)
                .into(moviePoster);
    }


    @Override
    public void showTrailers(List<TrailerModel> trailers) {
        trailerSection.setVisibility(View.VISIBLE);

        this.trailersll.removeAllViews();
        LayoutInflater inflater = getBaseActivity().getLayoutInflater();
        RequestOptions options = new RequestOptions()
                .placeholder(R.color.colorPrimary)
                .centerCrop()
                .override(150, 150);

        for (TrailerModel trailer : trailers) {
            View thumbContainer = inflater.inflate(R.layout.video_model, this.trailersll, false);
            ImageView thumbView = ButterKnife.findById(thumbContainer, R.id.video_poster);
            thumbView.requestLayout();
            thumbView.setOnClickListener(view -> movieDetailsPresenter.onTrailerClicked(ApiConstants.BASE_YOUTUBE_URL + "?v=" + trailer.getKey()));
            Glide.with(context())
                    .load(String.format(ApiConstants.POSTER_YOUTUBE_URL, trailer.getKey()))
                    .apply(options)
                    .into(thumbView);
            this.trailersll.addView(thumbContainer);
        }
    }

    @Override
    public void startTrailerIntent(String url) {
        Intent playVideoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(playVideoIntent);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRefreshing() {
//not implemented
    }

    @Override
    public void hideRefreshing() {
//not implemented
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public Context context() {
        return getBaseActivity().getApplicationContext();
    }
}
