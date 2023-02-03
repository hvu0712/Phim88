package com.example.phim88.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phim88.databinding.ItemMovieBinding;
import com.example.phim88.model.detail.Detail;
import com.example.phim88.model.detail.Genre;
import com.example.phim88.model.discover.Result;

import java.util.List;

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MoreViewHolder> {

    private List<Result> resultList;

    private Context context;

    public MoreAdapter(Context context) {
        this.context = context;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public MoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MoreViewHolder(itemMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreViewHolder holder, int position) {
        Result result = resultList.get(position);
        String img_base = "https://image.tmdb.org/t/p/original/";
        if (result == null){
            return;
        }
        holder.binding.tvName.setText(result.getTitle());
        Glide.with(context)
                .load(img_base + result.getBackdropPath())
                .into(holder.binding.imgItem);
    }

    @Override
    public int getItemCount() {
        if (resultList != null){
            return resultList.size();
        }
        return 0;
    }

    public class MoreViewHolder extends RecyclerView.ViewHolder {

        public ItemMovieBinding binding;

        public MoreViewHolder(@NonNull ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
