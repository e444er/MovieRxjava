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
import com.moon.movierxjava.utils.Constants;

import java.util.List;


public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieListViewHolder> {

   List<Movie> mMovies;
   private Context context;
   private OnMovieClickListener onMovieClickListener;



   public MoviesListAdapter(List<Movie> Movies  , Context context , OnMovieClickListener onMovieClickListener) {
      mMovies = Movies;
      this.context = context;
      this.onMovieClickListener = onMovieClickListener;

   }


   @NonNull
   @Override
   public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View MovieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
      return new MovieListViewHolder(MovieView , onMovieClickListener);
   }

   @Override
   public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {

      Movie movie = mMovies.get(position);

      Glide.with(context)
              .load(Constants.IMAGE_URL + movie.getPosterPath())
              .into(holder.MovieImage);

   }

   @Override
   public int getItemCount() {
      return mMovies.size();
   }

   public  class MovieListViewHolder extends RecyclerView.ViewHolder{

      ImageView MovieImage;
      OnMovieClickListener onMovieClickListener;



      public MovieListViewHolder(View itemMovieView , OnMovieClickListener onMovieClickListener) {
         super(itemMovieView);

         MovieImage = itemMovieView.findViewById(R.id.upcoming_image_view);
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


