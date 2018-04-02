package com.esevinale.movieguidetmdb.data.entity.mapper;

import com.esevinale.movieguidetmdb.data.entity.images.BackdropEntity;
import com.esevinale.movieguidetmdb.data.entity.images.ImagesEntity;
import com.esevinale.movieguidetmdb.data.entity.images.PosterEntity;
import com.esevinale.movieguidetmdb.domain.entity.image.Backdrop;
import com.esevinale.movieguidetmdb.domain.entity.image.Images;
import com.esevinale.movieguidetmdb.domain.entity.image.Poster;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieImagesMapper {
    @Inject
    public MovieImagesMapper() {
    }

    public Backdrop transformBackdrop(BackdropEntity backdropEntity) {
        return new Backdrop(backdropEntity.getAspectRatio(), backdropEntity.getFilePath(), backdropEntity.getHeight(), backdropEntity.getWidth());
    }

    public Poster transformPoster(PosterEntity posterEntity) {
        return new Poster(posterEntity.getAspectRatio(), posterEntity.getFilePath(), posterEntity.getHeight(), posterEntity.getWidth());
    }

    public Images transformImages(ImagesEntity imagesEntity) {
        List<Backdrop> backdrops = new ArrayList<>();
        List<Poster> posters = new ArrayList<>();
        for (BackdropEntity backdropEntity : imagesEntity.getBackdrops()) {
            if (backdropEntity != null)
                backdrops.add(transformBackdrop(backdropEntity));
        }
        for (PosterEntity posterEntity : imagesEntity.getPosters()) {
            if (posterEntity != null)
                posters.add(transformPoster(posterEntity));
        }
        return new Images(imagesEntity.getId(), backdrops, posters);
    }
}
