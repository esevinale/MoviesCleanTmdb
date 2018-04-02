package com.esevinale.movieguidetmdb.data.repository;

import com.esevinale.movieguidetmdb.data.entity.mapper.MovieImagesMapper;
import com.esevinale.movieguidetmdb.data.entity.mapper.TrailerMapper;
import com.esevinale.movieguidetmdb.data.repository.datasource.image.ImageDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerCouldDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.trailer.TrailerDataStore;
import com.esevinale.movieguidetmdb.domain.entity.Trailer;
import com.esevinale.movieguidetmdb.domain.entity.image.Images;
import com.esevinale.movieguidetmdb.domain.repository.ImageRepository;
import com.esevinale.movieguidetmdb.domain.repository.TrailerRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

public class MovieImagesRepository implements ImageRepository {

    private final MovieImagesMapper imagesMapper;
    private final ImageDataStore imageDataStore;

    @Inject
    public MovieImagesRepository(MovieImagesMapper imagesMapper, ImageDataStore imageDataStore) {
        this.imagesMapper = imagesMapper;
        this.imageDataStore = imageDataStore;
    }

    @Override
    public Flowable<Images> images(int id) {
        return imageDataStore.movieImages(id).map(imagesMapper::transformImages);
    }
}
