package com.esevinale.movieguidetmdb.data.entity.mapper;

import com.esevinale.movieguidetmdb.data.entity.tvShow.TvShowEntity;
import com.esevinale.movieguidetmdb.domain.entity.TvShow;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TvShowMapper {
    @Inject
    TvShowMapper() {
    }

    public TvShow transform(TvShowEntity tvShowEntity) {
        TvShow tvShow = new TvShow();
        tvShow.setBackdropPath(tvShowEntity.getBackdropPath());
        tvShow.setFirstAirDate(tvShowEntity.getFirstAirDate());
        tvShow.setGenreIds(tvShowEntity.getGenreIds());
        tvShow.setId(tvShowEntity.getId());
        tvShow.setName(tvShowEntity.getName());
        tvShow.setOriginalLanguage(tvShowEntity.getOriginalLanguage());
        tvShow.setOriginCountry(tvShowEntity.getOriginCountry());
        tvShow.setOriginalName(tvShowEntity.getOriginalName());
        tvShow.setOverview(tvShowEntity.getOverview());
        tvShow.setPosterPath(tvShowEntity.getPosterPath());
        tvShow.setVoteCount(tvShowEntity.getVoteCount());
        tvShow.setFirstAirDate(tvShowEntity.getFirstAirDate());
        tvShow.setVoteAverage(tvShowEntity.getVoteAverage());
        return tvShow;
    }

    public List<TvShow> transformList(List<TvShowEntity> tvShowEntityList) {
        List<TvShow> tvShows = new ArrayList<>();
        for (TvShowEntity tvShowEntity : tvShowEntityList) {
            if (tvShowEntity != null) {
                tvShows.add(transform(tvShowEntity));
            }
        }
        return tvShows;
    }
}
