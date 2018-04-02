package com.esevinale.movieguidetmdb.domain.interactor;

import com.esevinale.movieguidetmdb.domain.entity.Movie;
import com.esevinale.movieguidetmdb.domain.executor.PostExecutionThread;
import com.esevinale.movieguidetmdb.domain.executor.ThreadExecutor;
import com.esevinale.movieguidetmdb.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class GetPopularMovieList extends UseCase<List<Movie>, Integer> {

    private final MovieRepository movieRepository;

    @Inject
    public GetPopularMovieList(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MovieRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    Flowable<List<Movie>> buildUseCaseFlowable(Integer page) {
        return movieRepository.popularMovies(page);
    }
}
