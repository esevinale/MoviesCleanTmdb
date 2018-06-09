package com.esevinale.movieguidetmdb.data.repository.datasource.search;

import com.esevinale.movieguidetmdb.data.net.MovieService;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerCouldDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SearchDataStoreFactory {

    private final MovieService mMovieService;

    @Inject
    SearchDataStoreFactory(MovieService movieService) {
        this.mMovieService = movieService;
    }

    public SearchDataStore create() {
        return new SearchCloudDataStore(mMovieService);
    }
}
