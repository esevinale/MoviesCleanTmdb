package com.esevinale.movieguidetmdb.presentation.mapper;

import com.esevinale.movieguidetmdb.domain.entity.TvShow;
import com.esevinale.movieguidetmdb.presentation.model.TvShowModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class TvShowModelMapper {
    @Inject
    TvShowModelMapper() {
    }

    public TvShowModel transform(TvShow tvShow) {
        if (tvShow == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        TvShowModel tvShowModel = new TvShowModel();
        tvShowModel.setId(tvShow.getId());
        tvShowModel.setName(tvShow.getName());
        tvShowModel.setBackdropPath(tvShow.getBackdropPath());
        tvShowModel.setPosterPath(tvShow.getPosterPath());
        return tvShowModel;
    }

    public List<TvShowModel> transform(List<TvShow> tvShowList) {
        List<TvShowModel> tvShowModelList;
        if (tvShowList != null && !tvShowList.isEmpty()) {
            tvShowModelList = new ArrayList<>();
            for (TvShow tvShow : tvShowList) {
                tvShowModelList.add(transform(tvShow));
            }
        } else {
            tvShowModelList = Collections.emptyList();
        }

        return tvShowModelList;
    }
}
