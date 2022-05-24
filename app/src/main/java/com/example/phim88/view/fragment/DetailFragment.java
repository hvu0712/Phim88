package com.example.phim88.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.phim88.R;
import com.example.phim88.view.activity.MainActivity;
import com.example.phim88.view.adapter.MyViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class DetailFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);

        myViewPagerAdapter = new MyViewPagerAdapter(((MainActivity) getActivity()).getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(myViewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);


        return view;
    }
}
