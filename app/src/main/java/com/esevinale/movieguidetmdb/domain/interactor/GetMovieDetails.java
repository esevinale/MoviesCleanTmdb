package com.esevinale.movieguidetmdb.domain.interactor;

import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetails;
import com.esevinale.movieguidetmdb.domain.executor.PostExecutionThread;
import com.esevinale.movieguidetmdb.domain.executor.ThreadExecutor;
import com.esevinale.movieguidetmdb.domain.repository.MovieDetailsRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class GetMovieDetails extends UseCase<MovieDetails, Integer> {

    private final MovieDetailsRepository mDetailsRepository;

    @Inject
    GetMovieDetails(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MovieDetailsRepository detailsRepository) {
        super(threadExecutor, postExecutionThread);
        this.mDetailsRepository = detailsRepository;
    }

    @Override
    Flowable<MovieDetails> buildUseCaseFlowable(Integer id) {
        return mDetailsRepository.movieDetails(id);
    }
}
