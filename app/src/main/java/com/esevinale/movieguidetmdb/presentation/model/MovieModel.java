package com.esevinale.movieguidetmdb.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.esevinale.movieguidetmdb.presentation.model.details.GenreModel;

import java.util.ArrayList;
import java.util.List;

public class MovieModel implements Parcelable {

//    private Integer voteCount;
    private Integer id;
//    private Boolean video;
//    private Double voteAverage;
    private String title;
//    private Double popularity;
    private String posterPath;
//    private String originalLanguage;
//    private String originalTitle;
    private String backdropPath;
//    private Boolean adult;
//    private String overview;
//    private String releaseDate;


    public MovieModel(Integer id, String title, String posterPath, String backdropPath) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

    public MovieModel(Integer voteCount, Integer id, Boolean video, Double voteAverage, String title, Double popularity, String posterPath, String originalLanguage, String originalTitle,
                      String backdropPath, Boolean adult, String overview, String releaseDate) {
//        this.voteCount = voteCount;
        this.id = id;
//        this.video = video;
//        this.voteAverage = voteAverage;
        this.title = title;
//        this.popularity = popularity;
        this.posterPath = posterPath;
//        this.originalLanguage = originalLanguage;
//        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
//        this.adult = adult;
//        this.overview = overview;
//        this.releaseDate = releaseDate;
    }
//
//    public Integer getVoteCount() {
//        return voteCount;
//    }
//
//    public void setVoteCount(Integer voteCount) {
//        this.voteCount = voteCount;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Boolean getVideo() {
//        return video;
//    }
//
//    public void setVideo(Boolean video) {
//        this.video = video;
//    }
//
//    public Double getVoteAverage() {
//        return voteAverage;
//    }
//
//    public void setVoteAverage(Double voteAverage) {
//        this.voteAverage = voteAverage;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public Double getPopularity() {
//        return popularity;
//    }
//
//    public void setPopularity(Double popularity) {
//        this.popularity = popularity;
//    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

//    public String getOriginalLanguage() {
//        return originalLanguage;
//    }
//
//    public void setOriginalLanguage(String originalLanguage) {
//        this.originalLanguage = originalLanguage;
//    }
//
//    public String getOriginalTitle() {
//        return originalTitle;
//    }
//
//    public void setOriginalTitle(String originalTitle) {
//        this.originalTitle = originalTitle;
//    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

//    public Boolean getAdult() {
//        return adult;
//    }
//
//    public void setAdult(Boolean adult) {
//        this.adult = adult;
//    }
//
//    public String getOverview() {
//        return overview;
//    }
//
//    public void setOverview(String overview) {
//        this.overview = overview;
//    }
//
//    public String getReleaseDate() {
//        return releaseDate;
//    }
//
//    public void setReleaseDate(String releaseDate) {
//        this.releaseDate = releaseDate;
//    }

    protected MovieModel(Parcel in) {
//        this.voteCount = in.readInt();
        this.id = in.readInt();
//        this.video = in.readByte() != 0;
//        this.voteAverage = in.readDouble();
        this.title = in.readString();
//        this.popularity = in.readDouble();
        this.posterPath = in.readString();
//        this.originalLanguage = in.readString();
//        this.originalTitle = in.readString();
        this.backdropPath = in.readString();
//        this.adult = in.readByte() != 0;
//        this.overview = in.readString();
//        this.releaseDate = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
//        parcel.writeByte((byte) (video ? 1 : 0));
//        parcel.writeDouble(voteAverage);
        parcel.writeString(title);
//        parcel.writeDouble(popularity);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
//        parcel.writeString(overview);
//        parcel.writeString(releaseDate);
//        parcel.writeInt(voteCount);
//        parcel.writeString(originalLanguage);
//        parcel.writeString(originalTitle);
//        parcel.writeByte((byte) (adult ? 1 : 0));
    }
}