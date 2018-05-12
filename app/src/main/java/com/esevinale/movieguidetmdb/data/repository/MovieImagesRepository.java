package com.esevinale.movieguidetmdb.data.repository;

import com.esevinale.movieguidetmdb.data.entity.mapper.MovieImagesMapper;
import com.esevinale.movieguidetmdb.data.repository.datasource.image.ImageDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.image.ImageDataStoreFactory;
import com.esevinale.movieguidetmdb.domain.entity.image.Images;
import com.esevinale.movieguidetmdb.domain.repository.ImageRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MovieImagesRepository implements ImageRepository {

    private final MovieImagesMapper mImagesMapper;
    private final ImageDataStoreFactory mImageDataStore;

    @Inject
    MovieImagesRepository(MovieImagesMapper imagesMapper, ImageDataStoreFactory imageDataStore) {
        this.mImagesMapper = imagesMapper;
        this.mImageDataStore = imageDataStore;
    }

    @Override
    public Flowable<Images> images(int id) {
        return mImageDataStore.create().movieImages(id).map(mImagesMapper::transformImages);
    }
}
