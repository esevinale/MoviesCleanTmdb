package com.esevinale.movieguidetmdb.data.entity.mapper;

import com.esevinale.movieguidetmdb.data.entity.movieDetails.CastEntity;
import com.esevinale.movieguidetmdb.data.entity.movieDetails.CreditsEntity;
import com.esevinale.movieguidetmdb.domain.entity.details.Cast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieCreditsMapper {

    @Inject
    MovieCreditsMapper() {
    }

    public Cast transform(CastEntity castEntity){
        return new Cast(castEntity.getCharacter(), castEntity.getGender(), castEntity.getId(), castEntity.getName(), castEntity.getProfilePath());
    }

    public List<Cast> transformList(List<CastEntity> castList){
        List<Cast> credits = new ArrayList<>();
        for(CastEntity castEntity : castList){
            if(castEntity!=null){
                credits.add(transform(castEntity));
            }
        }
        return credits;
    }
}
