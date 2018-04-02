package com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters;

import com.esevinale.movieguidetmdb.domain.interactor.GetUpcomingMovieList;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelDataMapper;

import javax.inject.Inject;

@PerActivity
public class MovieUpcomingPresenter extends MovieListPresenter {
    @Inject
    public MovieUpcomingPresenter(GetUpcomingMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        super(getMovieList, movieModelDataMapper);
    }
}
