package com.moon.movierxjava.data.repository;

import com.moon.movierxjava.data.db.MovieDao;
import com.moon.movierxjava.data.model.Movie;
import com.moon.movierxjava.data.model.MovieListResponse;
import com.moon.movierxjava.data.network.MovieApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class MovieRepository {

    private final MovieApiService apiService;

    private MovieDao movieDao;

    @Inject
    public MovieRepository(MovieApiService apiService, MovieDao movieDao) {
        this.apiService = apiService;
        this.movieDao = movieDao;
    }

    public Single<MovieListResponse> getTrendMovies(String mediaType, String time, String apiKey) {
        return apiService.getTrendMovies(mediaType, time, apiKey);
    }

    public Single<MovieListResponse> getUpcomingMovies(String apiKey) {
        return apiService.getUpcomingMovies(apiKey);
    }

    //
    public Single<MovieListResponse> getSearchMovies(String apiKey, String query, int page) {
        return apiService.searchMovie(apiKey, query, page);
    }

    public Single<MovieListResponse> getTopRatedMovies(String apiKey, String language, int page) {
        return apiService.getTopRatedMovies(apiKey, language, page);
    }

    public Completable insert(Movie movie) {
        return movieDao.insert(movie);
    }

    public Single<List<Movie>> allMovies() {
        return movieDao.allWishlistMovies();
    }
}
