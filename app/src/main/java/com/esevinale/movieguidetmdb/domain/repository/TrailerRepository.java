package com.esevinale.movieguidetmdb.domain.repository;

import com.esevinale.movieguidetmdb.domain.entity.Trailer;

import java.util.List;

import io.reactivex.Flowable;

public interface TrailerRepository {
    Flowable<List<Trailer>> trailers(int id);
}
