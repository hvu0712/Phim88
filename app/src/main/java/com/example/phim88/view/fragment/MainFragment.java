package com.example.phim88.view.fragment;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phim88.R;
import com.example.phim88.control.Repository;
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
    private final List<Category> listCategory1 = new ArrayList<>();
    private final List<Popular> movieListPopular = new ArrayList<>();
    private final List<Upcoming> movieListUpcoming = new ArrayList<>();
    private FragmentMainBinding binding;
    private CategoryAdapter categoryAdapter;
    private PopularViewModel popularViewModel;
    private UpcomingViewModel upcomingViewModel;
    private Category category;
    private Category upcoming;
    private LinearLayoutManager linearLayoutManager;

    private ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback(){
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            Log.e(TAG, "onAvailable: ");
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    categoryAdapter = new CategoryAdapter();

                    linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

//                    fetchPopular();
//
//                    fetchUpcoming();
                }
            });

        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            Log.e(TAG, "onLost: ");
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            final boolean unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
            final boolean unmetered1 = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            Log.e(TAG, "onCapabilitiesChanged: "+unmetered+ " "+unmetered1);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();

        ConnectivityManager connectivityManager =
                (ConnectivityManager) binding.getRoot().getContext().getSystemService(ConnectivityManager.class);
        connectivityManager.requestNetwork(networkRequest, networkCallback);

        categoryAdapter = new CategoryAdapter();

        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        fetchPopular();

        fetchUpcoming();
        binding.rcvCategory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE){
                    Log.e(TAG, "categoryCount: " + categoryAdapter.getItemCount());
                }
                return false;
            }
        });

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

            Log.e(TAG, "fetchPopular1: "+listCategory);
            categoryAdapter.setData(listCategory);
            binding.rcvCategory.setAdapter(categoryAdapter);
            binding.rcvCategory.setLayoutManager(linearLayoutManager);

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
            Log.e(TAG, "fetchPopular2: "+listCategory);
            categoryAdapter.setData(listCategory);
            binding.rcvCategory.setAdapter(categoryAdapter);
            binding.rcvCategory.setLayoutManager(linearLayoutManager);
        });
        upcomingViewModel.RequestUpcoming();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getViewModelStore().clear();
    }
}
