package com.moon.movierxjava.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.moon.movierxjava.data.model.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDB extends RoomDatabase {

    public abstract MovieDao movieDao();
}