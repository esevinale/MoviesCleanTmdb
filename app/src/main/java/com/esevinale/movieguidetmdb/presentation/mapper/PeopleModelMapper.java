package com.esevinale.movieguidetmdb.presentation.mapper;


import com.esevinale.movieguidetmdb.domain.entity.people.People;
import com.esevinale.movieguidetmdb.presentation.internal.di.scopes.PerActivity;
import com.esevinale.movieguidetmdb.presentation.model.PeopleModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class PeopleModelMapper {

    @Inject
    PeopleModelMapper() {
    }

    public PeopleModel transform(People people) {
        if (people == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        PeopleModel peopleModel = new PeopleModel();
        peopleModel.setId(people.getId());
        peopleModel.setName(people.getName());
        peopleModel.setProfilePath(people.getProfilePath());
        return peopleModel;
    }

    public List<PeopleModel> transform(List<People> peopleList) {
        List<PeopleModel> peopleModelList;
        if (peopleList != null && !peopleList.isEmpty()) {
            peopleModelList = new ArrayList<>();
            for (People people : peopleList) {
                peopleModelList.add(transform(people));
            }
        } else {
            peopleModelList = Collections.emptyList();
        }

        return peopleModelList;
    }
}
