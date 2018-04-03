package com.esevinale.movieguidetmdb.domain.entity.image;

import java.util.List;

public class Images {
    private Integer id;
    private List<Backdrop> backdrops;
    private List<Poster> posters;

    public Images(Integer id, List<Backdrop> backdrops, List<Poster> posters) {
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

    public List<Backdrop> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Backdrop> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Poster> getPosters() {
        return posters;
    }

    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }
}
