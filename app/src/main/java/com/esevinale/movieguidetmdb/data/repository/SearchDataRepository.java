package com.esevinale.movieguidetmdb.data.repository;

import com.esevinale.movieguidetmdb.data.entity.mapper.SearchDataComposer;
import com.esevinale.movieguidetmdb.data.repository.datasource.search.SearchDataStore;
import com.esevinale.movieguidetmdb.data.repository.datasource.search.SearchDataStoreFactory;
import com.esevinale.movieguidetmdb.domain.entity.SearchDataDomainModel;
import com.esevinale.movieguidetmdb.domain.repository.SearchRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class SearchDataRepository implements SearchRepository {

    private final SearchDataStoreFactory mSearchDataStoreFactory;
    private final SearchDataComposer mSearchDataComposer;

    @Inject
    public SearchDataRepository(SearchDataStoreFactory mSearchDataStoreFactory, SearchDataComposer mSearchDataComposer) {
        this.mSearchDataStoreFactory = mSearchDataStoreFactory;
        this.mSearchDataComposer = mSearchDataComposer;
    }

    @Override
    public Flowable<SearchDataDomainModel> searchData(String query) {
        SearchDataStore searchDataStore = mSearchDataStoreFactory.create();
        return Flowable.zip(searchDataStore.movieSearch(query), searchDataStore.peopleSearch(query), searchDataStore.tvSearch(query), mSearchDataComposer::composeToModel);
    }
}
