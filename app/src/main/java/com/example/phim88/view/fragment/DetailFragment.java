package com.example.phim88.view.fragment;

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
import com.example.phim88.view.activity.MainActivity;
import com.example.phim88.view.adapter.MyViewPagerAdapter;
import com.example.phim88.viewmodel.DetailViewModel;


public class DetailFragment extends BaseFragment {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private static final String TAG = "DetailFragment";
    private FragmentDetailBinding binding;
    private DetailViewModel detailViewModel;

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
//        PublishSubject<String> subject = PublishSubject.create();
//        subject.subscribe();
//        Log.e(TAG, "fetchImg: "+subject.subscribe());
        String title = getArguments().getString("title");
        String img = getArguments().getString("img");
        String img_base = "https://image.tmdb.org/t/p/w500/";
        int id = getArguments().getInt("id");
        Log.e(TAG, "id: " + id);
        Glide.with(this)
                .load(img_base + img)
                .into(binding.imageView2);
        binding.textView6.setText(title);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.getListDetail().observe(getViewLifecycleOwner(), details -> {
            Log.e(TAG, "fetchImg: " + details.size());
        });
        detailViewModel.RequestListDetail();
    }

    public interface Callback {
        void back();
    }
}
