package com.moon.movierxjava.data.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Movie implements Serializable {

    @SerializedName(value="title", alternate={"name"})
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName(value="release_date", alternate={"first_air_date"})
    private String releaseDate;

    @PrimaryKey
    @SerializedName("id")
    private int moveID;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("overview")
    private String movieOverview;

    @SerializedName("original_language")
    private String originalLanguage;



    public Movie(String title, String posterPath, String releaseDate, int moveID, float voteAverage, String movieOverview ,String originalLanguage) {
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.moveID = moveID;
        this.voteAverage = voteAverage;
        this.movieOverview = movieOverview;
        this.originalLanguage = originalLanguage;

    }


    public Movie() {
    }


    // Getters

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getMoveID() {
        return moveID;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setMoveID(int moveID) {
        this.moveID = moveID;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
}