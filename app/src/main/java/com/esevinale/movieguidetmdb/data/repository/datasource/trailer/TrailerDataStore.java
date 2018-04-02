package com.esevinale.movieguidetmdb.data.repository.datasource.trailer;

import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerEntity;

import java.util.List;

import io.reactivex.Flowable;

public interface TrailerDataStore {
    Flowable<List<TrailerEntity>> movieTrailers(int id);
}
