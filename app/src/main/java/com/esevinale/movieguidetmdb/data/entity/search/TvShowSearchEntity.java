package com.esevinale.movieguidetmdb.data.entity.search;

import java.util.List;

import com.esevinale.movieguidetmdb.data.entity.tvShow.TvShowEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvShowSearchEntity {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<TvShowEntity> results = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<TvShowEntity> getResults() {
        return results;
    }

    public void setResults(List<TvShowEntity> results) {
        this.results = results;
    }

}
