package com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters;

import com.esevinale.movieguidetmdb.domain.interactor.GetUpcomingMovieList;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelMapper;

import javax.inject.Inject;

@PerActivity
public class MovieUpcomingPresenter extends MovieListPresenter {
    @Inject
    MovieUpcomingPresenter(GetUpcomingMovieList getMovieList, MovieModelMapper movieModelDataMapper) {
        super(getMovieList, movieModelDataMapper);
    }
}
