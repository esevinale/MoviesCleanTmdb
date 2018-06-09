package com.esevinale.movieguidetmdb.domain.interactor;

import com.esevinale.movieguidetmdb.domain.entity.SearchDataDomainModel;
import com.esevinale.movieguidetmdb.domain.executor.PostExecutionThread;
import com.esevinale.movieguidetmdb.domain.executor.ThreadExecutor;
import com.esevinale.movieguidetmdb.domain.repository.SearchRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class GetSearchData extends UseCase<SearchDataDomainModel, String> {

    private final SearchRepository mSearchRepository;
    @Inject
    GetSearchData(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, SearchRepository searchRepository) {
        super(threadExecutor, postExecutionThread);
        this.mSearchRepository = searchRepository;
    }

    @Override
    Flowable<SearchDataDomainModel> buildUseCaseFlowable(String s) {
        return mSearchRepository.searchData(s);
    }
}
