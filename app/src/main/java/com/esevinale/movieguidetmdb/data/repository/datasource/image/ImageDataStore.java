package com.esevinale.movieguidetmdb.data.repository.datasource.image;

import com.esevinale.movieguidetmdb.data.entity.images.ImagesEntity;
import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerEntity;
import com.esevinale.movieguidetmdb.domain.entity.image.Images;

import java.util.List;

import io.reactivex.Flowable;

public interface ImageDataStore {
    Flowable<ImagesEntity> movieImages(int id);
}
