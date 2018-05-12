package com.esevinale.movieguidetmdb.data.repository.datasource.details;

import com.esevinale.movieguidetmdb.data.entity.movieDetails.MovieDetailsEntity;
import com.esevinale.movieguidetmdb.data.net.MovieService;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MovieDetailsCouldDataStore implements MovieDetailsDataStore {
    private final MovieService mService;

    MovieDetailsCouldDataStore(MovieService service) {
        this.mService = service;
    }

    @Override
    public Flowable<MovieDetailsEntity> movieDetails(int id) {
        return mService.getMovieDetails(id);
    }
}
