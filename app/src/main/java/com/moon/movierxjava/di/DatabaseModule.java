package com.moon.movierxjava.di;

import android.app.Application;

import androidx.room.Room;

import com.moon.movierxjava.data.db.MovieDB;
import com.moon.movierxjava.data.db.MovieDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    MovieDB provideMovieDatabase(Application application) {
        return Room.databaseBuilder(
                        application,
                        MovieDB.class,
                        "movie-database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    MovieDao provideWishListDao(MovieDB MovieDB) {
        return MovieDB.movieDao();
    }
}
