package com.esevinale.movieguidetmdb.presentation.mapper;

import com.esevinale.movieguidetmdb.domain.entity.Trailer;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.model.TrailerModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class TrailerModelDataMapper {

    @Inject
    TrailerModelDataMapper() {
    }

    public TrailerModel transform(Trailer trailer) {
        if (trailer == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        return new TrailerModel(trailer.getId(), trailer.getKey(), trailer.getName(), trailer.getSite());
    }

    public List<TrailerModel> transform(List<Trailer> trailerList) {
        List<TrailerModel> trailerModelList;
        if (trailerList != null && !trailerList.isEmpty()) {
            trailerModelList = new ArrayList<>();
            for (Trailer trailer : trailerList) {
                trailerModelList.add(transform(trailer));
            }
        } else {
            trailerModelList = Collections.emptyList();
        }

        return trailerModelList;
    }
}
