package com.esevinale.movieguidetmdb.presentation.model;

public class PeopleModel {
    private Integer id;
    private String profilePath;
    private String name;

    public PeopleModel(Integer id, String profilePath, String name) {
        this.id = id;
        this.profilePath = profilePath;
        this.name = name;
    }

    public PeopleModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
