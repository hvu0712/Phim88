package com.example.phim88.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.phim88.R;
import com.example.phim88.databinding.MorePopularFragmentBinding;
import com.example.phim88.model.popular.Popular;
import com.example.phim88.view.adapter.MovieAdapter;
import com.example.phim88.viewmodel.PopularViewModel;

import java.util.ArrayList;
import java.util.List;

public class MorePopularFragment extends BaseFragment {

    MorePopularFragmentBinding binding;
    MovieAdapter movieAdapter;
    PopularViewModel popularViewModel;
    List<Popular> movieListPopular = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.more_popular_fragment, container, false);

        popularViewModel = new ViewModelProvider(this).get(PopularViewModel.class);
        popularViewModel.getListPopular().observe(getViewLifecycleOwner(), populars -> {
            if (populars != null && populars.size() > 0) {
                for (Popular popular : populars) {
                    movieListPopular.add(new Popular(popular.getPosterPath(), popular.getTitle(), popular.getId(), popular.getOverview(), popular.getVoteAverage(), popular.getGenreIds(), popular.getAdult(), popular.getBackdropPath()));
                }
            }
            movieAdapter = new MovieAdapter(getContext());

            if (movieListPopular != null && movieListPopular.size() > 0) {
                movieAdapter.setData(movieListPopular);
            }

            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            binding.rclvPopular.setLayoutManager(layoutManager);
            Log.e("TAG", "onCreateView: " + movieListPopular.size());
            Log.e("TAG", "onCreateView: " + movieAdapter.getItemCount());
            binding.rclvPopular.setAdapter(movieAdapter);

        });
        popularViewModel.requestPopular();
        return binding.getRoot();
    }
}
