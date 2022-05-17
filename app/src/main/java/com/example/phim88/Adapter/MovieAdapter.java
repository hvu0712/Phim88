package com.example.phim88.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phim88.R;
import com.example.phim88.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> mListMovie;




    public void setData(List<Movie> list){
        this.mListMovie = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = mListMovie.get(position);
        if(movie == null){
            return;
        }

        holder.imgmovie.setImageResource(movie.getImgMovie());
        holder.namemovie.setText(movie.getNameMovie());

    }

    @Override
    public int getItemCount() {
        if(mListMovie != null){
            return mListMovie.size();
        }
        return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgmovie ;
        private TextView namemovie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgmovie = itemView.findViewById(R.id.img_movie);
            namemovie = itemView.findViewById(R.id.tv_name_movie);
        }
    }
}
