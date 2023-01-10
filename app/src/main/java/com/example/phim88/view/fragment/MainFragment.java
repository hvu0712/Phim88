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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phim88.R;
import com.example.phim88.databinding.FragmentMainBinding;
import com.example.phim88.model.Category;
import com.example.phim88.model.popular.Popular;
import com.example.phim88.model.upcoming.Upcoming;
import com.example.phim88.view.adapter.CategoryAdapter;
import com.example.phim88.viewmodel.PopularViewModel;
import com.example.phim88.viewmodel.UpcomingViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BaseFragment {

    private static final String TAG = "MainFragment";
    private final List<Category> listCategory = new ArrayList<>();
    private final List<Popular> movieListPopular = new ArrayList<>();
    private final List<Upcoming> movieListUpcoming = new ArrayList<>();
    private FragmentMainBinding binding;
    private CategoryAdapter categoryAdapter;
    private PopularViewModel popularViewModel;
    private UpcomingViewModel upcomingViewModel;
    private Category category;
    private Category upcoming;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        categoryAdapter = new CategoryAdapter();

        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        fetchPopular();

        fetchUpcoming();

        Log.e(TAG, "onCreateView: " + movieListPopular);

        return binding.getRoot();
    }


    public void fetchPopular() {
        // get value popular

        popularViewModel = new ViewModelProvider(this).get(PopularViewModel.class);
        popularViewModel.getListPopular().observe(getViewLifecycleOwner(), populars -> {
            if (populars != null && populars.size() > 0) {
                for (Popular popular : populars) {
                    movieListPopular.add(new Popular(popular.getPosterPath(), popular.getTitle(), popular.getId(), popular.getOverview(), popular.getVoteAverage(), popular.getGenreIds(), popular.getAdult(), popular.getBackdropPath()));
                }

                listCategory.add(category);
            }
            category = new Category() {
                {
                    setMovies(movieListPopular);
                    setNameCategory("Popular");
                }
            };
            Log.e(TAG, "fetchPopular: ");
            categoryAdapter.setData(listCategory);
            binding.rcvCategory.setAdapter(categoryAdapter);
            binding.rcvCategory.setLayoutManager(linearLayoutManager);

        });
        popularViewModel.requestPopular();
    }

    public void getPopulars() {
        popularViewModel = new ViewModelProvider(this).get(PopularViewModel.class);
        popularViewModel.getListPopular().observe(getViewLifecycleOwner(), populars -> {

        });
        popularViewModel.requestPopular();
    }

    public void fetchUpcoming() {
        // get value upcoming
        upcomingViewModel = new ViewModelProvider(this).get(UpcomingViewModel.class);
        upcomingViewModel.getListUpcoming().observe(getViewLifecycleOwner(), upcomings -> {
            if (upcomings != null && upcomings.size() > 0) {
                for (Upcoming upcoming : upcomings) {
                    movieListUpcoming.add(new Upcoming(upcoming.getAdult(), upcoming.getId(), upcoming.getOverview(), upcoming.getPosterPath(), upcoming.getTitle(), upcoming.getVoteAverage(), upcoming.getBackdropPath()));
                }
                listCategory.add(upcoming);
            }
            upcoming = new Category() {
                {
                    setNameCategory("Upcoming");
                    setUpcomings(movieListUpcoming);
                }
            };
            categoryAdapter.setData(listCategory);
            binding.rcvCategory.setAdapter(categoryAdapter);
            binding.rcvCategory.setLayoutManager(linearLayoutManager);
        });
        upcomingViewModel.RequestUpcoming();
    }
}
