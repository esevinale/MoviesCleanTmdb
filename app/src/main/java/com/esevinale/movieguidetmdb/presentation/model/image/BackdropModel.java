package com.esevinale.movieguidetmdb.presentation.model.image;

public class BackdropModel {
    private Double aspectRatio;
    private String filePath;
    private Integer height;
    private Integer width;

    public BackdropModel(Double aspectRatio, String filePath, Integer height, Integer width) {
        this.aspectRatio = aspectRatio;
        this.filePath = filePath;
        this.height = height;
        this.width = width;
    }

    public Double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

}