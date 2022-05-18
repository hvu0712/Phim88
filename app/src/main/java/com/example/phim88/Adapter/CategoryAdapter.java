package com.example.phim88.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.phim88.R;
import com.example.phim88.model.Category;
import com.example.phim88.view.fragment.MainFragment;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context mContext;
    private List<Category> mList;

    public CategoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Category> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = mList.get(position);
        if (category == null) {
            return;
        }
        holder.tvGenre.setText(category.getNameCategory());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);

        holder.rcvMovie.setLayoutManager(staggeredGridLayoutManager);


        MovieAdapter movieAdapter = new MovieAdapter();
        movieAdapter.setData(category.getMovies());

        holder.rcvMovie.setAdapter(movieAdapter);
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvGenre;
        Button btnMore;
        RecyclerView rcvMovie;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGenre = itemView.findViewById(R.id.tv_genre);
            ////         btnMore = itemView.findViewById(R.id.btn_more);
            rcvMovie = itemView.findViewById(R.id.rcv_movie);
        }
    }
}
