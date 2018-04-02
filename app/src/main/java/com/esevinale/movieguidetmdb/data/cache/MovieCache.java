package com.esevinale.movieguidetmdb.data.cache;

import com.esevinale.movieguidetmdb.data.entity.movies.MovieEntity;
import com.esevinale.movieguidetmdb.data.entity.movies.MovieTypes;

import java.util.List;

import io.reactivex.Flowable;

public interface MovieCache {

    boolean isExpired();

    boolean isCached();

    Flowable<List<MovieEntity>> get(int page, MovieTypes movieType);

    void put(List<MovieEntity> movieEntities);
}
