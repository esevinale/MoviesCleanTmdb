package com.esevinale.movieguidetmdb.presentation.presenter;

import com.esevinale.movieguidetmdb.domain.interactor.GetTopRatedMovieList;
import com.esevinale.movieguidetmdb.domain.interactor.GetUpcomingMovieList;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelDataMapper;

public class MovieTopRatedPresenter extends MovieListPresenter {
    public MovieTopRatedPresenter(GetTopRatedMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        super(getMovieList, movieModelDataMapper);
    }
}
