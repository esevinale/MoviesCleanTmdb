package com.esevinale.movieguidetmdb.presentation.view;

import android.view.View;

import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetails;
import com.esevinale.movieguidetmdb.domain.entity.image.Images;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.model.TrailerModel;
import com.esevinale.movieguidetmdb.presentation.model.details.CastModel;
import com.esevinale.movieguidetmdb.presentation.model.details.MovieDetailsModel;
import com.esevinale.movieguidetmdb.presentation.model.image.ImagesModel;

import java.util.List;

public interface MovieDetailsView extends LoadDataView {
    void showMovieDetails(MovieDetailsModel movieModel);
    void showMovieImages(String backdrop, String poster);
    void showTrailers(List<TrailerModel> video);
    void showCast(List<CastModel> cast);
    void startTrailerIntent(String url);

    void onCastClicked(CastModel castModel, View view);
    void viewCast(CastModel castModel);
}
