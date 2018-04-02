package com.esevinale.movieguidetmdb.data.repository.datasource.details;

import com.esevinale.movieguidetmdb.data.entity.images.ImagesEntity;
import com.esevinale.movieguidetmdb.data.entity.movieDetails.MovieDetailsEntity;

import io.reactivex.Flowable;

public interface MovieDetailsDataStore {
    Flowable<MovieDetailsEntity> movieDetails(int id);
}
