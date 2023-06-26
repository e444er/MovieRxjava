package com.moon.movierxjava.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.moon.movierxjava.data.model.MovieListResponse;
import com.moon.movierxjava.data.network.MovieApiService;
import com.moon.movierxjava.data.repository.MovieRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final MovieRepository repository;
    private final MutableLiveData<MovieListResponse> movieTrend = new MutableLiveData<>();
    private final MutableLiveData<MovieListResponse> movieUpcomingList = new MutableLiveData<>();
    private final MutableLiveData<MovieListResponse> movieTopRated = new MutableLiveData<>();

    @Inject
    public HomeViewModel(MovieRepository repository) {
        this.repository = repository;
    }


    public LiveData<MovieListResponse> getTrendMovies(String mediaType, String time, String apiKey) {


        // retrofit call
        repository.getTrendMovies(mediaType, time, apiKey)
                // upstream works in io thread.
                .subscribeOn(Schedulers.io())
                // downstream works in mainThread.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MovieListResponse movieListResponse) {
                        movieTrend.setValue(movieListResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG","Trend Movies Error : " + e.getMessage());
                    }
                });

        return movieTrend;

    }

    // this method returns liveData which can be observed
    public LiveData<MovieListResponse> getUpcomingMovies(String apiKey){

        // retrofit call
        repository.getUpcomingMovies(apiKey)
                // upstream works in io thread.
                .subscribeOn(Schedulers.io())
                // downstream works in mainThread.
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new SingleObserver<MovieListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MovieListResponse movieListResponse) {
                        movieUpcomingList.setValue(movieListResponse);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e("TAG","Upcoming Movies Error : " + error.getMessage());

                    }
                });


        return movieUpcomingList;
    }



    // this method returns liveData which can be observed
    public LiveData<MovieListResponse> getTopRatedMovies(String apiKey , String language , int page){

        // retrofit call
        repository.getTopRatedMovies(apiKey,language,page)
                // ReactiveX provides two methods that allow defining on which thread the operations should be executed and on which thread the result should be handled: observeOn and subscribeOn.
                // upstream works in io thread.
                .subscribeOn(Schedulers.io())
                // downstream works in mainThread.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onSuccess(MovieListResponse movieListResponse) {
                        movieTopRated.setValue(movieListResponse);

                    }
                    @Override
                    public void onError(Throwable error) {
                        Log.e("TAG","Search Movies Error : " + error.getMessage());

                    }
                });

        return movieTopRated;
    }

}
