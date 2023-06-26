package com.moon.movierxjava.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moon.movierxjava.data.model.Movie;
import com.moon.movierxjava.databinding.ActivitySearchBinding;
import com.moon.movierxjava.presentation.adapter.OnMovieClickListener;
import com.moon.movierxjava.presentation.adapter.SearchMoviesAdapter;
import com.moon.movierxjava.presentation.viewmodel.SearchViewModel;
import com.moon.movierxjava.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchActivity extends AppCompatActivity implements OnMovieClickListener {

    private SearchViewModel searchViewModel;

    private ActivitySearchBinding binding;
    private List<Movie> searchList = new ArrayList<>();
    private SearchMoviesAdapter listAdapter;
    private int currentPage =1;
    private int totalAvailablePages =1;
    private Timer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        binding.backButton.setOnClickListener(view ->  onBackPressed());

        viewsInitialization();

    }

    private void observeAnyChangesSearch(String query){

        searchViewModel.getSearchMovies(Constants.API_KEY , query , 1)
                .observe(this, movieListResponse -> {
                    searchList.clear();


                    if(movieListResponse != null) {

                        binding.topSearchesText.setVisibility(View.VISIBLE);
                        searchList.addAll(movieListResponse.getResult());
                        listAdapter.notifyDataSetChanged();
                    }

                });
    }

    private void viewsInitialization(){

        // recycler

        listAdapter = new SearchMoviesAdapter(searchList, this,this);
        binding.rvSearchNews.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.rvSearchNews.setAdapter(listAdapter);

        // search input

        binding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(timer != null){
                    timer.cancel();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().trim().isEmpty()){
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    currentPage = 1;
                                    totalAvailablePages = 1;
                                    observeAnyChangesSearch(editable.toString());
                                }
                            });

                        }
                    },800L);
                }else {
                    searchList.clear();
                    binding.topSearchesText.setVisibility(View.GONE);
                    listAdapter.notifyDataSetChanged();

                }

            }
        });
        binding.rvSearchNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(!binding.rvSearchNews.canScrollVertically(1)){
                    if(!binding.searchEditText.getText().toString().isEmpty()){
                        if (currentPage < totalAvailablePages){
                            currentPage +=1;
                            observeAnyChangesSearch(binding.searchEditText.getText().toString());
                        }
                    }
                }
            }
        });
        binding.searchEditText.requestFocus();



    }


    @Override
    public void onMovieClicked(Movie movie) {

        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra("movie",movie);
        startActivity(intent);
    }
}