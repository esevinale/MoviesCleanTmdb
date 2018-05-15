
package com.esevinale.movieguidetmdb.data.entity.movieDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditsEntity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cast")
    @Expose
    private List<CastEntity> cast = null;
    @SerializedName("crew")
    @Expose
    private List<CrewEntity> crew = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CastEntity> getCast() {
        return cast;
    }

    public void setCast(List<CastEntity> cast) {
        this.cast = cast;
    }

    public List<CrewEntity> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewEntity> crew) {
        this.crew = crew;
    }

}
