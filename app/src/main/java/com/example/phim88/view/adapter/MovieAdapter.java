package com.example.phim88.view.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phim88.R;
import com.example.phim88.control.Repository;
import com.example.phim88.databinding.FragmentDetailBinding;
import com.example.phim88.databinding.FragmentSearchBinding;
import com.example.phim88.databinding.ItemMovieBinding;
import com.example.phim88.model.popular.Popular;
import com.example.phim88.view.activity.BaseActivity;
import com.example.phim88.view.fragment.DetailFragment;
import com.example.phim88.view.fragment.MainFragment;
import com.example.phim88.viewmodel.DetailViewModel;
import com.example.phim88.viewmodel.PopularViewModel;

import java.util.List;
import java.util.Observable;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Popular> mListMovie;
    private static final String TAG = "MovieAdapter";
    FragmentDetailBinding binding1;



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
         binding1 = FragmentDetailBinding.inflate(inflater, parent, false);
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
                Toast.makeText(view.getContext(), String.valueOf(popular.getId()), Toast.LENGTH_SHORT).show();
                binding1.textView6.setText(popular.getTitle());
                Bundle bundle = new Bundle();
                bundle.putString("title", popular.getTitle());
                bundle.putString("img", popular.getPosterPath());
                bundle.putInt("id", popular.getId());
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out)
                        .replace(R.id.fragment_container, detailFragment)
                        .addToBackStack(null)
                        .commit();
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

    public Popular getSelectedPopular(int position){
        if (mListMovie != null){
            if (mListMovie.size() > 0){
                return mListMovie.get(position);
            }
        }
        return null;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {


        public ItemMovieBinding binding;

        public MovieViewHolder(@NonNull ItemMovieBinding binding) {

            super(binding.getRoot());
            this.binding = binding;

//            binding.layoutItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                    Fragment detailFragment = new DetailFragment();
//                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
//                    FragmentTransaction transaction = fragmentManager.beginTransaction();
//                    transaction.setCustomAnimations(R.anim.slide_in,
//                            R.anim.fade_out,
//                            R.anim.fade_in,
//                            R.anim.slide_out)
//                            .replace(R.id.fragment_container, detailFragment)
//                            .addToBackStack(null)
//                            .commit();
//
//                    BaseActivity baseActivity = new BaseActivity();
//                    baseActivity.initFragment(R.id.fragment_container, detailFragment);
//                }
//            });
        }
    }
}
