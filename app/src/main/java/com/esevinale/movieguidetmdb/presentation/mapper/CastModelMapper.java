package com.esevinale.movieguidetmdb.presentation.mapper;

import com.esevinale.movieguidetmdb.domain.entity.details.Cast;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.model.details.CastModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class CastModelMapper {

    @Inject
    CastModelMapper() {
    }
    public CastModel transform(Cast cast) {
        if (cast == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        return new CastModel(cast.getCharacter(), cast.getGender(), cast.getId(), cast.getName(), cast.getProfilePath());
    }

    public List<CastModel> transform(List<Cast> castList) {
        List<CastModel> castModelList;
        if (castList != null && !castList.isEmpty()) {
            castModelList = new ArrayList<>();
            for (Cast cast : castList) {
                castModelList.add(transform(cast));
            }
        } else {
            castModelList = Collections.emptyList();
        }

        return castModelList;
    }
}
