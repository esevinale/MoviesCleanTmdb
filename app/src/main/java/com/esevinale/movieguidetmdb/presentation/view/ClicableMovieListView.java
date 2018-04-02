package com.esevinale.movieguidetmdb.presentation.view;

import com.esevinale.movieguidetmdb.presentation.model.MovieModel;

public interface ClicableMovieListView extends MovieListView {
    void onMovieClicked(MovieModel movie);
}
