package com.moon.movierxjava.presentation.viewmodel;

import android.util.Log;


import androidx.lifecycle.ViewModel;


import com.moon.movierxjava.data.model.Movie;
import com.moon.movierxjava.data.repository.MovieRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class DetailsViewModel extends ViewModel {

   private final MovieRepository mRepository;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
   public DetailsViewModel(MovieRepository mRepository) {
       this.mRepository = mRepository;
   }

    public void insert(Movie movie) {
       // completable observable
        compositeDisposable.add(mRepository.insert(movie)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(() -> {
        }, error -> Log.e("DetailsViewModel", error.getMessage()) ));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}