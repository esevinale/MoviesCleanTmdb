package com.esevinale.movieguidetmdb.data.entity.images;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesEntity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("backdrops")
    @Expose
    private List<BackdropEntity> backdrops = null;
    @SerializedName("posters")
    @Expose
    private List<PosterEntity> posters = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<BackdropEntity> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<BackdropEntity> backdrops) {
        this.backdrops = backdrops;
    }

    public List<PosterEntity> getPosters() {
        return posters;
    }

    public void setPosters(List<PosterEntity> posters) {
        this.posters = posters;
    }
}
