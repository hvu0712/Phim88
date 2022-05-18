package com.example.phim88.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phim88.Adapter.CategoryAdapter;
import com.example.phim88.R;
import com.example.phim88.databinding.FragmentMainBinding;
import com.example.phim88.model.Category;
import com.example.phim88.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BaseFragment {

    FragmentMainBinding binding;
    CategoryAdapter categoryAdapter;
    RecyclerView rcvHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container, false);

        categoryAdapter = new CategoryAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.rcvCategory.setLayoutManager(linearLayoutManager);



        categoryAdapter.setData(getListCategory());
        binding.rcvCategory.setAdapter(categoryAdapter);



        return binding.getRoot();
    }
    private List<Category> getListCategory() {

        List<Category> listCategory = new ArrayList<>();

        List<Movie> listMovie = new ArrayList<>();

        listMovie.add(new Movie(R.drawable.demo1, "Phim Hanh Dong 1 "));
        listMovie.add(new Movie(R.drawable.demo1, "Phim Hanh Dong 2 "));
        listMovie.add(new Movie(R.drawable.demo1, "Phim Hanh Dong 3 "));
        listMovie.add(new Movie(R.drawable.demo1, "Phim Hanh Dong 4 "));
        listMovie.add(new Movie(R.drawable.demo1, "Phim Hanh Dong 5 "));
        listMovie.add(new Movie(R.drawable.demo1, "Phim Hanh Dong 6 "));
        listMovie.add(new Movie(R.drawable.demo1, "Phim Hanh Dong 6 "));
        listMovie.add(new Movie(R.drawable.demo1, "Phim Hanh Dong 7 "));
        listMovie.add(new Movie(R.drawable.demo1, "Phim Hanh Dong 8 "));
        listMovie.add(new Movie(R.drawable.demo1, "Phim Hanh Dong 9 "));
        listMovie.add(new Movie(R.drawable.demo1, "Phim Hanh Dong 10 "));

        listCategory.add(new Category("Phim hanh dong 1", listMovie));
        listCategory.add(new Category("Phim hanh dong 2", listMovie));
        listCategory.add(new Category("Phim hanh dong 3     ", listMovie));

        return listCategory;
    }
}
