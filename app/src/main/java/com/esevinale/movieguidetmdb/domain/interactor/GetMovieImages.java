package com.esevinale.movieguidetmdb.domain.interactor;

import com.esevinale.movieguidetmdb.domain.entity.image.Images;
import com.esevinale.movieguidetmdb.domain.executor.PostExecutionThread;
import com.esevinale.movieguidetmdb.domain.executor.ThreadExecutor;
import com.esevinale.movieguidetmdb.domain.repository.ImageRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class GetMovieImages extends UseCase<Images, Integer> {

    private final ImageRepository imageRepository;

    @Inject
    GetMovieImages(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ImageRepository imageRepository) {
        super(threadExecutor, postExecutionThread);
        this.imageRepository = imageRepository;
    }

    @Override
    Flowable<Images> buildUseCaseFlowable(Integer id) {
        return imageRepository.images(id);
    }
}
