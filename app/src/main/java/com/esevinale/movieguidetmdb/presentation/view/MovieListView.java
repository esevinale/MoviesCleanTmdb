package com.esevinale.movieguidetmdb.presentation.view;

import com.esevinale.movieguidetmdb.presentation.model.MovieModel;

import java.util.List;

public interface MovieListView extends LoadDataView {

    void renderMovieList(List<MovieModel> movieModelList);
    void addMoviesToList(List<MovieModel> movieModelList);

    void viewMovie(MovieModel movieModel);
}