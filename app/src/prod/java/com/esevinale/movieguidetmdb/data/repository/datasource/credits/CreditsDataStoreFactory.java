package com.esevinale.movieguidetmdb.data.repository.datasource.credits;

import com.esevinale.movieguidetmdb.data.net.MovieService;

import javax.inject.Inject;

public class CreditsDataStoreFactory {

    private final MovieService mMovieService;

    @Inject
    CreditsDataStoreFactory(MovieService movieService) {
        this.mMovieService = movieService;
    }

    public CreditsDataStore create() {
        return new CreditsCloudDataStore(mMovieService);
    }
}
