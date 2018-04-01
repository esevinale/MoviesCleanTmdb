package com.esevinale.movieguidetmdb.data.cache;

import com.esevinale.movieguidetmdb.data.entity.MovieEntity;
import com.esevinale.movieguidetmdb.data.entity.MovieTypes;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface MovieCache {

    boolean isExpired();

    boolean isCached();

    Flowable<List<MovieEntity>> get(int page, MovieTypes movieType);

    void put(List<MovieEntity> movieEntities);
}
