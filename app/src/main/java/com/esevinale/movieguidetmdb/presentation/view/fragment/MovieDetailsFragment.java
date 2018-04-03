package com.esevinale.movieguidetmdb.presentation.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.data.net.ApiConstants;
import com.esevinale.movieguidetmdb.presentation.model.TrailerModel;
import com.esevinale.movieguidetmdb.presentation.model.details.GenreModel;
import com.esevinale.movieguidetmdb.presentation.model.details.MovieDetailsModel;
import com.esevinale.movieguidetmdb.presentation.model.details.ProductionCompanyModel;
import com.esevinale.movieguidetmdb.presentation.presenter.MovieDetailsPresenter;
import com.esevinale.movieguidetmdb.presentation.view.MovieDetailsView;
import com.esevinale.movieguidetmdb.presentation.view.utils.Constants;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class MovieDetailsFragment extends BaseFragment implements MovieDetailsView {

    @BindView(R.id.movie_name_collaps)
    TextView movieName;
    @BindView(R.id.sites)
    TextView movieSite;
    @BindView(R.id.movie_year)
    TextView movieYear;
    @BindView(R.id.movie_time)
    TextView movieTime;
    @BindView(R.id.movie_genres)
    TextView movieGenres;
    @BindView(R.id.companies)
    TextView movieCompanies;
//    @BindView(R.id.movie_rating)
//    TextView movieRate;
    @BindView(R.id.movie_overview_content)
    TextView movieOverview;
    @BindView(R.id.tv_budget)
    TextView movieBudget;
    @BindView(R.id.tv_revenue)
    TextView movieRevenue;
    @BindView(R.id.movie_backdrop)
    ImageView movieBackdrop;
    @BindView(R.id.movie_poster_collapse)
    ImageView moviePoster;
    @BindView(R.id.collapsedToolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.trailers_section)
    LinearLayout trailerSection;
    @BindView(R.id.ll_trailers)
    LinearLayout trailersll;
    @BindView(R.id.site_layout)
    LinearLayout siteLayout;

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
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setUpActionBar();
        return view;
    }


    private void setUpActionBar() {
        getBaseActivity().setSupportActionBar(toolbar);
        ActionBar actionBar = getBaseActivity().getSupportActionBar();
        if (actionBar == null)
            return;
        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getBaseActivity().getSupportActionBar().setDisplayShowHomeEnabled(true);
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

        if (TextUtils.isEmpty(movieModel.getHomepage()))
            siteLayout.setVisibility(View.GONE);
        else {
            siteLayout.setVisibility(View.VISIBLE);
            movieSite.setText(movieModel.getHomepage());
        }
        movieCompanies.setText(createStringForCompanies(movieModel.getProductionCompanies()));
        movieName.setText(movieModel.getTitle());
//        movieRate.setText(String.format(getString(R.string.movie_rating), String.valueOf(movieModel.getVoteAverage())));
        movieYear.setText(movieModel.getReleaseDate().substring(0, 4));
        int hrs = movieModel.getRuntime() / 60;
        int min = movieModel.getRuntime() - (hrs * 60);
        movieTime.setText(String.format(getString(R.string.movie_duration), String.valueOf(hrs), String.valueOf(min)));
        movieOverview.setText(movieModel.getOverview());
        movieGenres.setText(createStringForGenres(movieModel.getGenres()));
        movieBudget.setText(formatStringToDollars(movieModel.getBudget()));
        movieRevenue.setText(formatStringToDollars(movieModel.getRevenue()));
    }

    private String formatStringToDollars(int sum) {
        if (sum == 0)
            return getString(R.string.na_string);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);
        return "$" + formatter.format(sum);
    }

    private String createStringForCompanies(List<ProductionCompanyModel> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<ProductionCompanyModel> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getName());
            if (iterator.hasNext())
                sb.append(", ");
        }
        return sb.toString();
    }

    private String createStringForGenres(List<GenreModel> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<GenreModel> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getName());
            if (iterator.hasNext())
                sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public void showMovieImages(MovieDetailsModel movieDetailsModel) {
        Glide
                .with(context())
                .asBitmap()
                .load(ApiConstants.BACK_TMDB_URL + movieDetailsModel.getBackdropPath())
                .into(new BitmapImageViewTarget(movieBackdrop) {
                          @Override
                          public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                              super.onResourceReady(resource, transition);
                              Palette.from(resource).generate(palette -> setTitleColor(palette));
                          }
                      }
                );
        Glide
                .with(context())
                .load(ApiConstants.POSTER_TMDB_URL + movieDetailsModel.getPosterPath())
                .into(moviePoster);
    }

    private void setTitleColor(Palette palette) {
        int color = getResources().getColor(R.color.colorPrimary);
        if (palette.getDarkMutedSwatch() != null)
            color = palette.getDarkMutedSwatch().getRgb();
        collapsingToolbar.setBackgroundColor(color);
        collapsingToolbar.setContentScrimColor(color);
        collapsingToolbar.setStatusBarScrimColor(color);
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
