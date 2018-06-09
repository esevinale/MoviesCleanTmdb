package com.esevinale.movieguidetmdb.data.entity.mapper;

import com.esevinale.movieguidetmdb.data.entity.people.KnownForEntity;
import com.esevinale.movieguidetmdb.domain.entity.people.KnownFor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class KnownForMapper {
    @Inject
    KnownForMapper() {
    }

    public KnownFor transform(KnownForEntity knownForEntity) {
        KnownFor knownFor = new KnownFor();
        knownFor.setVoteCount(knownForEntity.getVoteCount());
        knownFor.setId(knownForEntity.getId());
        knownFor.setVideo(knownForEntity.getVideo());
        knownFor.setVoteAverage(knownForEntity.getVoteAverage());
        knownFor.setTitle(knownForEntity.getTitle());
        knownFor.setPopularity(knownForEntity.getPopularity());
        knownFor.setPosterPath(knownForEntity.getPosterPath());
        knownFor.setOriginalLanguage(knownForEntity.getOriginalLanguage());
        knownFor.setOriginalTitle(knownForEntity.getOriginalTitle());
        knownFor.setGenreIds(knownForEntity.getGenreIds());
        knownFor.setBackdropPath(knownForEntity.getBackdropPath());
        knownFor.setAdult(knownForEntity.getAdult());
        knownFor.setOverview(knownForEntity.getOverview());
        knownFor.setReleaseDate(knownForEntity.getReleaseDate());
        knownFor.setOriginalName(knownForEntity.getOriginalName());
        knownFor.setMediaType(knownForEntity.getMediaType());
        knownFor.setFirstAirDate(knownForEntity.getFirstAirDate());
        knownFor.setOriginCountry(knownForEntity.getOriginCountry());
        return knownFor;
    }

    public List<KnownFor> transformList(List<KnownForEntity> knownForEntities) {
        List<KnownFor> knownFors = new ArrayList<>();
        for (KnownForEntity knownForEntity : knownForEntities) {
            if (knownForEntity != null) {
                knownFors.add(transform(knownForEntity));
            }
        }
        return knownFors;
    }
}
