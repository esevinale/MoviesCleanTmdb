package com.esevinale.movieguidetmdb.data.repository.datasource.image;

import com.esevinale.movieguidetmdb.data.entity.images.ImagesEntity;
import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerEntity;
import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerResultDTO;
import com.esevinale.movieguidetmdb.data.net.MovieService;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerDataStore;
import com.esevinale.movieguidetmdb.domain.entity.image.Images;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

public class ImagesCouldDataStore implements ImageDataStore {
    private final MovieService service;

    @Inject
    public ImagesCouldDataStore(MovieService service) {
        this.service = service;
    }

    @Override
    public Flowable<ImagesEntity> movieImages(int id) {
        return service.getMovieImages(id);
    }
}
