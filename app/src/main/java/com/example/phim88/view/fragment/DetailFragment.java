package com.example.phim88.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.phim88.R;
import com.example.phim88.databinding.FragmentDetailBinding;
import com.example.phim88.databinding.ItemMovieBinding;
import com.example.phim88.model.Category;
import com.example.phim88.model.popular.Popular;
import com.example.phim88.view.activity.MainActivity;
import com.example.phim88.view.adapter.CategoryAdapter;
import com.example.phim88.view.adapter.MovieAdapter;
import com.example.phim88.view.adapter.MyViewPagerAdapter;
import com.example.phim88.viewmodel.PopularViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private SearchFragment.Callback callback;
    private PopularViewModel popularViewModel;
    private CategoryAdapter categoryAdapter = new CategoryAdapter();
    private List<Popular> movieListPopular = new ArrayList<>();
    private static final String TAG = "DetailFragment";
    private List<Category> listCategory = new ArrayList<>();
    private Category category;
    private FragmentDetailBinding binding;
    private ItemMovieBinding bindingItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        myViewPagerAdapter = new MyViewPagerAdapter(((MainActivity) getActivity()).getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewPager.setAdapter(myViewPagerAdapter);

        binding.tabLayout.setupWithViewPager(viewPager);
        fetchImg();

        return binding.getRoot();
    }


    public void fetchImg() {
        if (getActivity().getIntent().hasExtra("moviePopular")){
            Popular popular = getActivity().getIntent().getParcelableExtra("moviePopular");
            Log.e(TAG, "fetchImg: "+popular.getTitle());
        }
    }

    public interface Callback {
        void back();
    }
}
