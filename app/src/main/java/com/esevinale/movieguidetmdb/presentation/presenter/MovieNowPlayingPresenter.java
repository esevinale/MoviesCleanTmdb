package com.esevinale.movieguidetmdb.presentation.presenter;

import com.esevinale.movieguidetmdb.domain.interactor.GetNowPlayingMovieList;
import com.esevinale.movieguidetmdb.domain.interactor.GetUpcomingMovieList;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelDataMapper;

public class MovieNowPlayingPresenter extends MovieListPresenter {
    public MovieNowPlayingPresenter(GetNowPlayingMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        super(getMovieList, movieModelDataMapper);
    }
}
