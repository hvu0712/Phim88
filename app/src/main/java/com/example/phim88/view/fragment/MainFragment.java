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
import com.example.phim88.model.popular.Category;
import com.example.phim88.model.popular.Popular;
import com.example.phim88.view.adapter.CategoryAdapter;
import com.example.phim88.viewmodel.PopularViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BaseFragment {

    private static final String TAG = "MainFragment";
    private FragmentMainBinding binding;
    private CategoryAdapter categoryAdapter;
    private PopularViewModel popularViewModel;
    private List<Category> listCategory;
    private List<Popular> movieList;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        categoryAdapter = new CategoryAdapter();

        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        movieList = new ArrayList<>();
        listCategory = new ArrayList<>();
        popularViewModel = new ViewModelProvider(this).get(PopularViewModel.class);
        popularViewModel.getListPopular().observe(getViewLifecycleOwner(), populars -> {
            if (populars != null && populars.size() > 0){
                for (Popular popular : populars){
                    movieList.add(new Popular(popular.getPosterPath(), popular.getTitle()));
                }
                listCategory.add(new Category("Popular", movieList));
                listCategory.add(new Category("UpcomingApi", movieList));
                Log.e(TAG, "getItemCount: "+categoryAdapter.getItemCount());
            }
            categoryAdapter.setData(listCategory);
            binding.rcvCategory.setAdapter(categoryAdapter);
            binding.rcvCategory.setLayoutManager(linearLayoutManager);
        });
        popularViewModel.requestPopular();
        return binding.getRoot();
    }
}
