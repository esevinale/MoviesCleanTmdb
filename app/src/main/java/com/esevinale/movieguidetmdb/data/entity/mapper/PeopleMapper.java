package com.esevinale.movieguidetmdb.data.entity.mapper;

import com.esevinale.movieguidetmdb.data.entity.people.PeopleEntity;
import com.esevinale.movieguidetmdb.domain.entity.people.People;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PeopleMapper {

    private final KnownForMapper mKnownForMapper;

    @Inject
    PeopleMapper(KnownForMapper knownForMapper) {
        this.mKnownForMapper = knownForMapper;
    }

    public People transform(PeopleEntity peopleEntity) {
        People people = new People();
        people.setAdult(peopleEntity.getAdult());
        people.setId(peopleEntity.getId());
        people.setKnownFor(mKnownForMapper.transformList(peopleEntity.getKnownFor()));
        people.setName(peopleEntity.getName());
        people.setPopularity(peopleEntity.getPopularity());
        people.setProfilePath(peopleEntity.getProfilePath());
        return people;
    }

    public List<People> transformList(List<PeopleEntity> peopleEntityList) {
        List<People> peoples = new ArrayList<>();
        for (PeopleEntity peopleEntity : peopleEntityList) {
            if (peopleEntity != null) {
                peoples.add(transform(peopleEntity));
            }
        }
        return peoples;
    }
}
