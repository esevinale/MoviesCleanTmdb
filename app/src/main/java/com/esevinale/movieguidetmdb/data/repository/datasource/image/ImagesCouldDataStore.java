package com.esevinale.movieguidetmdb.data.repository.datasource.image;

import com.esevinale.movieguidetmdb.data.entity.images.ImagesEntity;
import com.esevinale.movieguidetmdb.data.net.MovieService;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class ImagesCouldDataStore implements ImageDataStore {
    private final MovieService mService;

    ImagesCouldDataStore(MovieService service) {
        this.mService = service;
    }

    @Override
    public Flowable<ImagesEntity> movieImages(int id) {
        return mService.getMovieImages(id);
    }
}
