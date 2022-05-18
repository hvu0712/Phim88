package com.example.phim88.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phim88.R;
import com.example.phim88.databinding.ItemMovieBinding;
import com.example.phim88.databinding.LvItemBinding;
import com.example.phim88.model.Movie;
import com.example.phim88.view.fragment.DetailFragment;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> mListMovie;


    public void setData(List<Movie> list) {
        this.mListMovie = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemMovieBinding binding = ItemMovieBinding.inflate(inflater, parent, false);

        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = mListMovie.get(position);
        if (movie == null) {
            return;
        }

        holder.binding.imgItem.setImageResource(movie.getImgMovie());
        holder.binding.tvName.setText(movie.getNameMovie());
        holder.binding.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                DetailFragment detailFragment = new DetailFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main,detailFragment).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mListMovie != null) {
            return mListMovie.size();
        }
        return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {


        public ItemMovieBinding binding;

        public MovieViewHolder(@NonNull ItemMovieBinding binding) {

            super(binding.getRoot());
          this.binding = binding;

        }
    }
}
