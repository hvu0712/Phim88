package com.example.phim88.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phim88.R;
import com.example.phim88.databinding.ItemMovieBinding;
import com.example.phim88.model.popular.Popular;
import com.example.phim88.view.fragment.DetailFragment;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Popular> mListMovie;


    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Popular> list) {
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
        Popular popular = mListMovie.get(position);
        if (popular == null) {
            return;
        }
        String img_base = "https://image.tmdb.org/t/p/w500/";
        holder.binding.tvName.setText(popular.getTitle());
        Glide.with(context)
                .load(img_base + popular.getPosterPath())
                .into(holder.binding.imgItem);
        holder.binding.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                DetailFragment detailFragment = new DetailFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, detailFragment).addToBackStack(null).commit();
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
