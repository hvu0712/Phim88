package com.example.phim88.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.phim88.R;
import com.example.phim88.databinding.FragmentSearchBinding;
import com.example.phim88.model.search.Result;
import com.example.phim88.view.adapter.SearchAdapter;
import com.example.phim88.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseFragment {
    private static final String TAG = "SearchFragment";

    private FragmentSearchBinding binding;

    public static String query1 = null;

    private Callback callback;

    private SearchViewModel searchViewModel;

    private SearchAdapter searchAdapter = new SearchAdapter();

    private List<Result> list = new ArrayList<>();


    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);


        Log.e(TAG, "onCreateViewSearch: ");
        binding.searchViewMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e(TAG, "onQueryTextSubmit: " + query);
                query1 = query;
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                searchViewModel = new ViewModelProvider(SearchFragment.this).get(SearchViewModel.class);
                searchViewModel.getListSearch().observe(getViewLifecycleOwner(), results -> {
                    if (results != null && results.size() > 0) {
                        for (Result result : results) {
                            list.add(result);
                            searchAdapter.setmList(list);
                            Log.e(TAG, "onCreateViewSearch: " + searchAdapter.getItemCount());
                        }
                    }

                });
                searchViewModel.requestSearch(query);
                binding.recyclerViewMovie.setAdapter(searchAdapter);
                binding.recyclerViewMovie.setLayoutManager(layoutManager);
                searchAdapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list.clear();
                searchAdapter.notifyDataSetChanged();
                return true;
            }
        });

        binding.searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) callback.back();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: ");
    }

    public interface Callback {
        void back();
    }
}
