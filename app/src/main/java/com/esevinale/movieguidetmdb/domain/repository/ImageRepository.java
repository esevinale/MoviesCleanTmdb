package com.esevinale.movieguidetmdb.domain.repository;

import com.esevinale.movieguidetmdb.domain.entity.image.Images;

import io.reactivex.Flowable;

public interface ImageRepository {
    Flowable<Images> images(int id);
}
