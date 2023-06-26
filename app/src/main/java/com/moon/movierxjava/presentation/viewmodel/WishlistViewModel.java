package com.moon.movierxjava.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.moon.movierxjava.data.model.Movie;
import com.moon.movierxjava.data.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


@HiltViewModel
public class WishlistViewModel extends ViewModel {
    private final MutableLiveData<List<Movie>> allWishlistMovies = new MutableLiveData<>();
    private final MovieRepository mRepository;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public WishlistViewModel(MovieRepository mRepository) {
        this.mRepository = mRepository;
    }

    public LiveData<List<Movie>> getAllWishlist() {

        compositeDisposable.add(mRepository.allMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( movies -> {
                    if(movies != null){
                        allWishlistMovies.setValue(movies);
                    }
                }, error -> Log.e("WishlistViewModel", error.getMessage()) ));
        return allWishlistMovies;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();

    }
}
