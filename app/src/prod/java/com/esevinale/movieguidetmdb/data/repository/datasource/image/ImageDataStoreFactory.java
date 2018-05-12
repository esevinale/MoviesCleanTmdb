package com.esevinale.movieguidetmdb.data.repository.datasource.image;

import com.esevinale.movieguidetmdb.data.cache.MovieCache;
import com.esevinale.movieguidetmdb.data.entity.mapper.MovieTypeMapper;
import com.esevinale.movieguidetmdb.data.net.ConnectionChecker;
import com.esevinale.movieguidetmdb.data.net.MovieService;
import com.esevinale.movieguidetmdb.data.repository.datasource.movie.MovieCloudDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.movie.MovieDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.movie.MovieLocalDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ImageDataStoreFactory {

    private final MovieService mMovieService;

    @Inject
    ImageDataStoreFactory(MovieService movieService) {
        this.mMovieService = movieService;
    }

    public ImageDataStore create() {
        return new ImagesCouldDataStore(mMovieService);
    }
}
