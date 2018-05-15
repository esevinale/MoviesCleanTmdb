package com.esevinale.movieguidetmdb.domain.interactor;

import com.esevinale.movieguidetmdb.domain.entity.details.MovieDetailsDomainModel;
import com.esevinale.movieguidetmdb.domain.executor.PostExecutionThread;
import com.esevinale.movieguidetmdb.domain.executor.ThreadExecutor;
import com.esevinale.movieguidetmdb.domain.repository.MovieDetailsRepository;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;

import javax.inject.Inject;

import io.reactivex.Flowable;

@PerActivity
public class GetMovieDetails extends UseCase<MovieDetailsDomainModel, Integer> {

    private final MovieDetailsRepository mMovieDetailsRepository;

    @Inject
    GetMovieDetails(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MovieDetailsRepository movieDetailsRepository) {
        super(threadExecutor, postExecutionThread);
        this.mMovieDetailsRepository = movieDetailsRepository;
    }

    @Override
    Flowable<MovieDetailsDomainModel> buildUseCaseFlowable(Integer id) {
        return mMovieDetailsRepository.movieDetails(id);
    }
}
