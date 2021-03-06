package com.esevinale.movieguidetmdb.data.entity.trailer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerResultDTO {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<TrailerEntity> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TrailerEntity> getResults() {
        return results;
    }

    public void setResults(List<TrailerEntity> results) {
        this.results = results;
    }

}