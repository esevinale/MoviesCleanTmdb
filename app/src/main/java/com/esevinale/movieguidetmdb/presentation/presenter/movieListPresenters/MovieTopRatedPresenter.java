package com.esevinale.movieguidetmdb.presentation.presenter.movieListPresenters;

import com.esevinale.movieguidetmdb.domain.interactor.GetTopRatedMovieList;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.mapper.MovieModelDataMapper;

import javax.inject.Inject;

@PerActivity
public class MovieTopRatedPresenter extends MovieListPresenter {
    @Inject
    public MovieTopRatedPresenter(GetTopRatedMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        super(getMovieList, movieModelDataMapper);
    }
}
