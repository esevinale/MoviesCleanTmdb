package com.esevinale.movieguidetmdb.domain.entity.people;

import java.util.List;

public class People {
    private Double popularity;
    private Integer id;
    private String profilePath;
    private String name;
    private List<KnownFor> knownFor = null;
    private Boolean adult;

    public People(Double popularity, Integer id, String profilePath, String name, List<KnownFor> knownFor, Boolean adult) {
        this.popularity = popularity;
        this.id = id;
        this.profilePath = profilePath;
        this.name = name;
        this.knownFor = knownFor;
        this.adult = adult;
    }

    public People() {
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
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

    public List<KnownFor> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<KnownFor> knownFor) {
        this.knownFor = knownFor;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }
}
