package com.example.phim88.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phim88.R;
import com.example.phim88.control.ItemClickListener;
import com.example.phim88.databinding.ItemMovieBinding;
import com.example.phim88.model.detail.Detail;
import com.example.phim88.model.detail.Genre;
import com.example.phim88.model.discover.Result;
import com.example.phim88.view.fragment.DetailFragment;

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
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        String img_base = "https://image.tmdb.org/t/p/original/";
        if (result == null){
            return;
        }
        holder.binding.tvName.setText(result.getTitle());
        Glide.with(context)
                .load(img_base + result.getBackdropPath())
                .into(holder.binding.imgItem);
        bundle.putInt("mId", result.getId());
        detailFragment.setArguments(bundle);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
//                    chưa có ý tưởng cho longClick, bao giờ có thêm sau
//                    initFragment(detailFragment, view);
                } else {
                    initFragment(detailFragment, view);
                    Log.e("TAG", "onClick: "+result.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (resultList != null){
            return resultList.size();
        }
        return 0;
    }

    private void initFragment(Fragment fragment, View view){
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public class MoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ItemMovieBinding binding;
        private ItemClickListener itemClickListener;

        public MoreViewHolder(@NonNull ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.getRoot().setOnClickListener(this);
            this.binding.getRoot().setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}
