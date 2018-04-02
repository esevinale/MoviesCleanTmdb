package com.esevinale.movieguidetmdb.presentation.model.image;

import java.util.List;

public class ImagesModel {
    private Integer id;
    private List<BackdropModel> backdrops = null;
    private List<PosterModel> posters = null;

    public ImagesModel(Integer id, List<BackdropModel> backdrops, List<PosterModel> posters) {
        this.id = id;
        this.backdrops = backdrops;
        this.posters = posters;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<BackdropModel> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<BackdropModel> backdrops) {
        this.backdrops = backdrops;
    }

    public List<PosterModel> getPosters() {
        return posters;
    }

    public void setPosters(List<PosterModel> posters) {
        this.posters = posters;
    }
}
