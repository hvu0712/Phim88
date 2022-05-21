package com.example.phim88.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.phim88.databinding.ItemCategoryBinding;
import com.example.phim88.model.popular.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> mList;

    public void setData(List<Category> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = mList.get(position);
        if (category == null) {
            return;
        }
        holder.movieAdapter.setData(category.getMovies());

        holder.binding.tvGenre.setText(category.getNameCategory());

        holder.binding.rcvMovie.setLayoutManager(holder.staggeredGridLayoutManager);

        holder.binding.rcvMovie.setAdapter(holder.movieAdapter);

    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        StaggeredGridLayoutManager staggeredGridLayoutManager;
        ItemCategoryBinding binding;
        MovieAdapter movieAdapter;

        public CategoryViewHolder(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
            movieAdapter = new MovieAdapter(binding.getRoot().getContext());
        }
    }
}
