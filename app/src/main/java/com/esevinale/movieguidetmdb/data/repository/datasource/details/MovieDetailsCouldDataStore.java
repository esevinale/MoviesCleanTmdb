package com.esevinale.movieguidetmdb.data.repository.datasource.details;

import com.esevinale.movieguidetmdb.data.entity.images.ImagesEntity;
import com.esevinale.movieguidetmdb.data.entity.movieDetails.MovieDetailsEntity;
import com.esevinale.movieguidetmdb.data.net.MovieService;
import com.esevinale.movieguidetmdb.data.repository.datasource.image.ImageDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

public class MovieDetailsCouldDataStore implements MovieDetailsDataStore {
    private final MovieService service;

    @Inject
    public MovieDetailsCouldDataStore(MovieService service) {
        this.service = service;
    }

    @Override
    public Flowable<MovieDetailsEntity> movieDetails(int id) {
        return service.getMovieDetails(id);
    }
}
