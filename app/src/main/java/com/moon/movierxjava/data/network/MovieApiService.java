package com.moon.movierxjava.data.network;

import com.moon.movierxjava.data.model.Movie;
import com.moon.movierxjava.data.model.MovieListResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("trending/{media_type}/{time_window}")
    Single<MovieListResponse> getTrendMovies(
            @Path("media_type") String mediaType,
            @Path("time_window") String time,
            @Query("api_key") String key);

    @GET("movie/upcoming")
    Single<MovieListResponse> getUpcomingMovies(@Query("api_key") String key);


    @GET("search/movie")
    Single<MovieListResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );

    @GET("movie/top_rated?")
    Single<MovieListResponse> getTopRatedMovies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );

}
