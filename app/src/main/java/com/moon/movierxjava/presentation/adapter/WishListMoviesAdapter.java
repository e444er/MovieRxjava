package com.moon.movierxjava.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.moon.movierxjava.R;
import com.moon.movierxjava.data.model.Movie;
import com.moon.movierxjava.utils.Constants;

import java.util.List;


public class WishListMoviesAdapter extends RecyclerView.Adapter<WishListMoviesAdapter.MovieListViewHolder> {

    public List<Movie> mMovies;
    private Context context;
    private OnMovieClickListener onMovieClickListener;


    public WishListMoviesAdapter(List<Movie> Movies, Context context, OnMovieClickListener onMovieClickListener) {
        mMovies = Movies;
        this.context = context;
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View MovieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_wish_list_item, parent, false);
        return new MovieListViewHolder(MovieView, onMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {

        Movie movie = mMovies.get(position);

        Glide.with(context)
                .load(Constants.IMAGE_URL + movie.getPosterPath())
                .into(holder.movieImage);

        holder.movieName.setText(movie.getTitle());
        holder.movieDate.setText(movie.getReleaseDate());

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView movieImage;
        TextView movieName;
        TextView movieDate;
        OnMovieClickListener onMovieClickListener;


        public MovieListViewHolder(View itemMovieView, OnMovieClickListener onMovieClickListener) {
            super(itemMovieView);

            movieImage = itemMovieView.findViewById(R.id.search_movie_image);
            movieName = itemMovieView.findViewById(R.id.search_movie_name);
            movieDate = itemMovieView.findViewById(R.id.search_movie_date);


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


