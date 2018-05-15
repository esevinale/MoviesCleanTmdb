package com.esevinale.movieguidetmdb.presentation.model.details;

public class CastModel {
    private String character;
    private Integer gender;
    private Integer id;
    private String name;
    private String profilePath;

    public CastModel(String character, Integer gender, Integer id, String name, String profilePath) {
        this.character = character;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.profilePath = profilePath;
    }

    public CastModel() {
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
