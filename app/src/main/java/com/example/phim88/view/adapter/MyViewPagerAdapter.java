package com.example.phim88.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.phim88.view.fragment.CastFragment;
import com.example.phim88.view.fragment.ProducerFragment;
import com.example.phim88.view.fragment.TrailerFragment;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

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
                return new CastFragment();

            case 2:
                return new ProducerFragment();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
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
