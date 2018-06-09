package com.esevinale.movieguidetmdb.presentation.view.views;

import android.view.View;

import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.model.PeopleModel;
import com.esevinale.movieguidetmdb.presentation.model.TvShowModel;

public interface ClickableSearchView {
    void onTvClicked(TvShowModel tvShowModel, View view);
    void onMovieClicked(MovieModel movieModel, View view);
    void onPeopleClicked(PeopleModel peopleModel, View view);
}
