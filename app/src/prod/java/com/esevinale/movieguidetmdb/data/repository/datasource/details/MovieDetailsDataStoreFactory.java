package com.esevinale.movieguidetmdb.data.repository.datasource.details;

import com.esevinale.movieguidetmdb.data.net.MovieService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MovieDetailsDataStoreFactory {

    private final MovieService mMovieService;

    @Inject
    MovieDetailsDataStoreFactory(MovieService movieService) {
        this.mMovieService = movieService;
    }

    public MovieDetailsDataStore create() {
        return new MovieDetailsCouldDataStore(mMovieService);
    }
}
