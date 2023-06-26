package com.moon.movierxjava.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.moon.movierxjava.data.model.Movie;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface MovieDao {

    @Insert
    Completable insert(Movie movie);

    @Query("SELECT * From Movie")
    Single<List<Movie>> allWishlistMovies();
}
