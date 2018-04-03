package com.esevinale.movieguidetmdb.domain.interactor;

import com.esevinale.movieguidetmdb.domain.entity.Trailer;
import com.esevinale.movieguidetmdb.domain.executor.PostExecutionThread;
import com.esevinale.movieguidetmdb.domain.executor.ThreadExecutor;
import com.esevinale.movieguidetmdb.domain.repository.TrailerRepository;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

@PerActivity
public class GetMovieTrailers extends UseCase<List<Trailer>, Integer> {

    private final TrailerRepository trailerRepository;

    @Inject
    GetMovieTrailers(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, TrailerRepository trailerRepository) {
        super(threadExecutor, postExecutionThread);
        this.trailerRepository = trailerRepository;
    }

    @Override
    Flowable<List<Trailer>> buildUseCaseFlowable(Integer id) {
        return trailerRepository.trailers(id);
    }
}
