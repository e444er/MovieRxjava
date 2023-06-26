package com.moon.movierxjava.data.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListResponse {
    @SerializedName("results")
    private List<Movie> result;
    @SerializedName("page")
    private int respondPageNumber;
    @SerializedName("total_pages")
    private int respondTotalPageNumber;

    public List<Movie> getResult() {
        return result;
    }

    public int getRespondPageNumber() {
        return respondPageNumber;
    }

    public int getRespondTotalPageNumber() {
        return respondTotalPageNumber;
    }

    @Override
    public String toString() {
        return "FilmsRespond{" +
                "result=" + result +
                ", respondPageNumber=" + respondPageNumber +
                ", respondTotalPageNumber=" + respondTotalPageNumber +
                '}';
    }
}