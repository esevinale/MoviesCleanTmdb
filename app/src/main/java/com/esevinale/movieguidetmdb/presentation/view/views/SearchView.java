package com.esevinale.movieguidetmdb.presentation.view.views;

import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.model.PeopleModel;
import com.esevinale.movieguidetmdb.presentation.model.TvShowModel;

import java.util.List;

public interface SearchView extends LoadDataView {
    void renderMovieList(List<MovieModel> movieModelList);
    void renderTvShowLost(List<TvShowModel> tvShowModelList);
    void renderPeopleList(List<PeopleModel> peopleModelList);
    void hideAll();

    void showEmptyLayout();
    void hideEmptyLayout();

    void startMovieActivity(MovieModel movieModel);
    void startTvActivity(TvShowModel tvShowModel);
    void startPersonActivity(PeopleModel peopleModel);
}
