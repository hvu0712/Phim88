package com.example.phim88.view.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.phim88.R;
import com.example.phim88.databinding.MorePopularFragmentBinding;
import com.example.phim88.model.popular.Popular;
import com.example.phim88.model.upcoming.Upcoming;
import com.example.phim88.view.adapter.MovieAdapter;
import com.example.phim88.viewmodel.PopularViewModel;
import com.example.phim88.viewmodel.UpcomingViewModel;

import java.util.ArrayList;
import java.util.List;

public class MorePopularFragment extends BaseFragment {

    private MorePopularFragmentBinding binding;
    private MovieAdapter movieAdapter;
    private PopularViewModel popularViewModel;
    private UpcomingViewModel upcomingViewModel;
    private List<Popular> movieListPopular = new ArrayList<>();
    private List<Upcoming> movieListUpcoming = new ArrayList<>();
    private int position;
    public static int p;
    private Bundle bundle;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.more_popular_fragment, container, false);
        bundle = getArguments();

        Log.e(TAG, "onCreateView1: "+bundle.getInt("position"));

        position = bundle.getInt("position");

        p = position;

        popularViewModel = new ViewModelProvider(this).get(PopularViewModel.class);
        upcomingViewModel = new ViewModelProvider(this).get(UpcomingViewModel.class);
        binding.tvTitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                activity.onBackPressed();
            }
        });

        if (bundle.getInt("position") == 0){
            binding.tvTitle.setTitle("Popular");
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
        } else if (bundle.getInt("position") == 1){
            binding.tvTitle.setTitle("Upcoming");
            upcomingViewModel.getListUpcoming().observe(getViewLifecycleOwner(), upcomings -> {
                if (upcomings != null && upcomings.size() > 0){
                    for (Upcoming upcoming : upcomings){
                        movieListUpcoming.add(new Upcoming(upcoming.getAdult(), upcoming.getId(), upcoming.getOverview(), upcoming.getPosterPath(), upcoming.getTitle(), upcoming.getVoteAverage(), upcoming.getBackdropPath()));
                    }
                }
                movieAdapter = new MovieAdapter(getContext());

                if (movieListUpcoming != null && movieListUpcoming.size() > 0){
                    movieAdapter.setDataUpcoming(movieListUpcoming);
                }

                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                binding.rclvPopular.setLayoutManager(layoutManager);
                Log.e("TAG", "onCreateView: " + movieListUpcoming.size());
                Log.e("TAG", "onCreateView: " + movieAdapter.getItemCount());
                binding.rclvPopular.setAdapter(movieAdapter);
            });
            upcomingViewModel.RequestUpcoming();
        }


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        bundle = getArguments();

        Log.e(TAG, "onCreateView1: "+bundle.getInt("position"));

        position = bundle.getInt("position");
    }

    @Override
    public void onStart() {
        super.onStart();
        bundle = getArguments();

        Log.e(TAG, "onCreateView1: "+bundle.getInt("position"));

        position = bundle.getInt("position");

    }

    @Override
    public void onStop() {
        super.onStop();
        bundle = getArguments();

        Log.e(TAG, "onCreateView1: "+bundle.getInt("position"));

        position = bundle.getInt("position");

    }

    @Override
    public void onPause() {
        super.onPause();
        bundle = getArguments();

        Log.e(TAG, "onCreateView1: "+bundle.getInt("position"));

        position = bundle.getInt("position");

    }
}
