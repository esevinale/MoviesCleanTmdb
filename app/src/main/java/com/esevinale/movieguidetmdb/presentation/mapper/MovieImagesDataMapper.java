package com.esevinale.movieguidetmdb.presentation.mapper;

import com.esevinale.movieguidetmdb.domain.entity.image.Backdrop;
import com.esevinale.movieguidetmdb.domain.entity.image.Images;
import com.esevinale.movieguidetmdb.domain.entity.image.Poster;
import com.esevinale.movieguidetmdb.presentation.model.image.BackdropModel;
import com.esevinale.movieguidetmdb.presentation.model.image.ImagesModel;
import com.esevinale.movieguidetmdb.presentation.model.image.PosterModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieImagesDataMapper {
    @Inject
    MovieImagesDataMapper() {
    }

    public BackdropModel transformBackdrop(Backdrop backdrop) {
        return new BackdropModel(backdrop.getAspectRatio(), backdrop.getFilePath(), backdrop.getHeight(), backdrop.getWidth());
    }

    public PosterModel transformPoster(Poster poster) {
        return new PosterModel(poster.getAspectRatio(), poster.getFilePath(), poster.getHeight(), poster.getWidth());
    }

    public ImagesModel transformImages(Images images) {
        List<BackdropModel> backdrops = new ArrayList<>();
        List<PosterModel> posters = new ArrayList<>();
        for (Backdrop backdrop : images.getBackdrops()) {
            if (backdrop != null)
                backdrops.add(transformBackdrop(backdrop));
        }
        for (Poster poster : images.getPosters()) {
            if (poster != null)
                posters.add(transformPoster(poster));
        }
        return new ImagesModel(images.getId(), backdrops, posters);
    }
}
