package com.esevinale.movieguidetmdb.data.repository.datasource.movie;

import com.esevinale.movieguidetmdb.data.cache.MovieCache;
import com.esevinale.movieguidetmdb.data.entity.mapper.MovieTypeMapper;
import com.esevinale.movieguidetmdb.data.net.ConnectionChecker;
import com.esevinale.movieguidetmdb.data.net.MovieService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MovieDataStoreFactory {
    private final MovieCache mMovieCache;
    private final MovieService mMovieService;
    private final MovieTypeMapper mTypeMapper;
    private final ConnectionChecker mIsConnection;

    @Inject
    MovieDataStoreFactory(MovieCache movieCache, MovieService movieService, MovieTypeMapper typeMapper, ConnectionChecker isConnection) {
        this.mMovieCache = movieCache;
        this.mMovieService = movieService;
        this.mTypeMapper = typeMapper;
        this.mIsConnection = isConnection;
    }

    public MovieDataStore create() {
        if (!mIsConnection.isOnline() && mMovieCache.isCached())
            return new MovieLocalDataStore(mMovieCache);
        else
            return new MovieCloudDataStore(mMovieCache, mMovieService, mTypeMapper);
    }
}
