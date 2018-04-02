package com.esevinale.movieguidetmdb.data.entity.mapper;

import com.esevinale.movieguidetmdb.data.entity.trailer.TrailerEntity;
import com.esevinale.movieguidetmdb.domain.entity.Trailer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TrailerMapper {
    @Inject
    public TrailerMapper() { }

    public Trailer transform(TrailerEntity trailerEntity){
        return new Trailer(trailerEntity.getId(), trailerEntity.getKey(), trailerEntity.getName(), trailerEntity.getSite());
    }

    public List<Trailer> transformList(List<TrailerEntity> trailerEntities){
        List<Trailer> trailer = new ArrayList<>();
        for(TrailerEntity trailerEntity:trailerEntities){
            if(trailerEntity!=null){
                trailer.add(transform(trailerEntity));
            }
        }
        return trailer;
    }
}
