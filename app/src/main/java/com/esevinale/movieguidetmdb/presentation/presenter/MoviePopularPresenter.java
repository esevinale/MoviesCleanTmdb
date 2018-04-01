package com.esevinale.movieguidetmdb.presentation.presenter;

import com.esevinale.movieguidetmdb.domain.interactor.GetPopularMovieList;
import com.esevinale.movieguidetmdb.domain.interactor.GetUpcomingMovieList;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelDataMapper;

public class MoviePopularPresenter extends MovieListPresenter {
    public MoviePopularPresenter(GetPopularMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        super(getMovieList, movieModelDataMapper);
    }
}
