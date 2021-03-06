package com.example.phim88.view.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.phim88.databinding.ItemCategoryBinding;
import com.example.phim88.model.Category;
import com.example.phim88.view.fragment.DetailFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> mList;
    private static final String TAG = "CategoryAdapter";
    private MovieAdapter movieAdapter;

    public CategoryAdapter() {
        mList = new ArrayList<>();
    }

    public void setData(List<Category> list) {
        if (list == null) {
            return;
        }
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Category category = mList.get(position);
        if (category == null) {
            return;
        }
        holder.movieAdapter.setData(category.getMovies());

        holder.upcomingAdapter.setUpcoming(category.getUpcomings());

        holder.binding.tvGenre.setText(category.getNameCategory());
        
        holder.binding.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.binding.getRoot().getContext(), DetailFragment.class);
                intent.putExtra("moviePopular", String.valueOf(movieAdapter.getSelectedPopular(position)));
            }
        });

        holder.binding.rcvMovie.setLayoutManager(holder.staggeredGridLayoutManager);

        holder.binding.rcvMovie.setAdapter(holder.concatAdapter);

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
        UpcomingAdapter upcomingAdapter;
        ConcatAdapter concatAdapter;

        public CategoryViewHolder(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
            upcomingAdapter = new UpcomingAdapter(binding.btnMore.getContext());
            movieAdapter = new MovieAdapter(binding.getRoot().getContext());
            concatAdapter = new ConcatAdapter(movieAdapter, upcomingAdapter);
        }
    }
}
