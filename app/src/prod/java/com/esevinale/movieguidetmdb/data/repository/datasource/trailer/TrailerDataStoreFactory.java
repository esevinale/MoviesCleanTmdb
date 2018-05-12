package com.esevinale.movieguidetmdb.data.repository.datasource.trailer;

import com.esevinale.movieguidetmdb.data.net.MovieService;
import com.esevinale.movieguidetmdb.data.repository.datasource.details.MovieDetailsCouldDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.details.MovieDetailsDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TrailerDataStoreFactory {

    private final MovieService mMovieService;

    @Inject
    TrailerDataStoreFactory(MovieService movieService) {
        this.mMovieService = movieService;
    }

    public TrailerDataStore create() {
        return new TrailerCouldDataStore(mMovieService);
    }
}
