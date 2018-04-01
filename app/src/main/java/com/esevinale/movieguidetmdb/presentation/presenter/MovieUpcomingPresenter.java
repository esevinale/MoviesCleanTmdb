package com.esevinale.movieguidetmdb.presentation.presenter;

import com.esevinale.movieguidetmdb.domain.interactor.GetUpcomingMovieList;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelDataMapper;

public class MovieUpcomingPresenter extends MovieListPresenter {
    public MovieUpcomingPresenter(GetUpcomingMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        super(getMovieList, movieModelDataMapper);
    }
}
