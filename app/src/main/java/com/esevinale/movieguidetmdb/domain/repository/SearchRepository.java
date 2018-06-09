package com.esevinale.movieguidetmdb.domain.repository;

import com.esevinale.movieguidetmdb.domain.entity.SearchDataDomainModel;

import io.reactivex.Flowable;

public interface SearchRepository {
    Flowable<SearchDataDomainModel> searchData(String search);
}
