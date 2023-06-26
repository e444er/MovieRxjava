package com.moon.movierxjava.presentation.viewmodel;

import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.moon.movierxjava.data.model.MovieListResponse;
import com.moon.movierxjava.data.repository.MovieRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class SearchViewModel extends ViewModel {

   private final MutableLiveData<MovieListResponse> movieSearch = new MutableLiveData<>();

   private final CompositeDisposable compositeDisposable = new CompositeDisposable();



   // constructor
   private final MovieRepository repository;


    @Inject
    public SearchViewModel(MovieRepository mRepository) {
        this.repository = mRepository;
    }


    // this method returns liveData which can be observed in the view
    public LiveData<MovieListResponse> getSearchMovies(String apiKey , String query , int page){


        compositeDisposable.add(repository.getSearchMovies(apiKey,query,page)

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe( movieSearchListResponse -> {

                    if(movieSearchListResponse != null){
                        movieSearch.setValue(movieSearchListResponse);
                    }

                }, error -> Log.e("SearchViewModel", error.getMessage()) ));

        return movieSearch;
    }




    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
