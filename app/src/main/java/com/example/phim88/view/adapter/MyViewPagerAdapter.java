package com.example.phim88.view.adapter;

import android.os.Parcelable;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.phim88.view.fragment.CatsFragment;
import com.example.phim88.view.fragment.ProducerFragment;
import com.example.phim88.view.fragment.TrailerFragment;

public class MyViewPagerAdapter extends FragmentPagerAdapter {



    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new TrailerFragment();

            case 1:
                return new CatsFragment();

            case 2:
                return new ProducerFragment();

            default:
                return new TrailerFragment();
        }

    }


    @Override
    public int getCount() {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tittle = "";
        switch (position) {
            case 0:
                tittle = "Trailer";
                break;

            case 1:
                tittle = "Cats";
                break;

            case 2:
                tittle = "Producer";
                break;
        }
        return tittle;
    }
}
