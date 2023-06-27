package com.moon.movierxjava.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moon.movierxjava.R;
import com.moon.movierxjava.data.model.Movie;
import com.moon.movierxjava.databinding.MovieTrendListItemBinding;
import com.moon.movierxjava.utils.Constants;

import java.util.List;

public class TrendMoviesAdapter extends RecyclerView.Adapter<TrendMoviesAdapter.MovieListViewHolder> {

    List<Movie> mMovies;
    private Context context;
    private OnMovieClickListener onMovieClickListener;


    public TrendMoviesAdapter(List<Movie> Movies  , Context context , OnMovieClickListener onMovieClickListener) {
        mMovies = Movies;
        this.context = context;
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        MovieTrendListItemBinding view = MovieTrendListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieListViewHolder(view, onMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {

        holder.bindItem(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder{
        ImageView MovieImage;
        OnMovieClickListener onMovieClickListener;
        public MovieListViewHolder(@NonNull MovieTrendListItemBinding view, OnMovieClickListener onMovieClickListener) {
            super(view.getRoot());
            MovieImage = view.trendImageView;
            this.onMovieClickListener = onMovieClickListener;

            itemView.setOnClickListener(v -> {
                onMovieClickListener.onMovieClicked(mMovies.get(getAdapterPosition()));
            });
        }

        public void bindItem(Movie movie) {
            Glide.with(context)
                    .load(Constants.IMAGE_URL + movie.getPosterPath())
                    .into(MovieImage);
        }
    }
}
