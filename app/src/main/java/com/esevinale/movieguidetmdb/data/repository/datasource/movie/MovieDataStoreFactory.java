package com.esevinale.movieguidetmdb.data.repository.datasource.movie;

import com.esevinale.movieguidetmdb.data.cache.MovieCache;
import com.esevinale.movieguidetmdb.data.entity.mapper.MovieTypeMapper;
import com.esevinale.movieguidetmdb.data.net.ConnectionChecker;
import com.esevinale.movieguidetmdb.data.net.MovieService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MovieDataStoreFactory {
    private final MovieCache movieCache;
    private final MovieService movieService;
    private final MovieTypeMapper typeMapper;
    private final ConnectionChecker isConnection;

    @Inject
    MovieDataStoreFactory(MovieCache movieCache, MovieService movieService, MovieTypeMapper typeMapper, ConnectionChecker isConnection) {
        this.movieCache = movieCache;
        this.movieService = movieService;
        this.typeMapper = typeMapper;
        this.isConnection = isConnection;
    }

    public MovieDataStore create() {
        if (!isConnection.isOnline() && movieCache.isCached())
            return new MovieLocalDataStore(movieCache);
        else
            return new MovieCloudDataStore(movieCache, movieService, typeMapper);
    }
}
