package com.moon.movierxjava.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moon.movierxjava.R;
import com.moon.movierxjava.data.model.Movie;
import com.moon.movierxjava.utils.Constants;

import java.util.List;


public class SearchMoviesAdapter extends RecyclerView.Adapter<SearchMoviesAdapter.MovieListViewHolder> {

    public List<Movie> mMovies;
    private Context context;
    private OnMovieClickListener onMovieClickListener;


    public SearchMoviesAdapter(List<Movie> Movies, Context context, OnMovieClickListener onMovieClickListener) {
        mMovies = Movies;
        this.context = context;
        this.onMovieClickListener = onMovieClickListener;

    }


    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View MovieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_search_list_item, parent, false);
        return new MovieListViewHolder(MovieView, onMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {

        Movie movie = mMovies.get(position);

        Glide.with(context)
                .load(Constants.IMAGE_URL + movie.getPosterPath())
                .into(holder.movieImage);

        holder.movieName.setText(movie.getTitle());

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder {

        ImageView movieImage;
        TextView movieName;
        OnMovieClickListener onMovieClickListener;


        public MovieListViewHolder(View itemMovieView, OnMovieClickListener onMovieClickListener) {
            super(itemMovieView);

            movieImage = itemMovieView.findViewById(R.id.backdrop_image);
            movieName = itemMovieView.findViewById(R.id.name_text);


            this.onMovieClickListener = onMovieClickListener;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onMovieClickListener.onMovieClicked(mMovies.get(getAdapterPosition()));

                }
            });
        }


    }
}


