package com.esevinale.movieguidetmdb.presentation.model;

public class TrailerModel {
    private String id;
    private String key;
    private String name;
    private String site;

    public TrailerModel(String id, String key, String name, String site) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
