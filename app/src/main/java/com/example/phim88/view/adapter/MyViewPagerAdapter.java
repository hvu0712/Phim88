package com.example.phim88.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.phim88.view.fragment.CatsFragment;
import com.example.phim88.view.fragment.ProducerFragment;
import com.example.phim88.view.fragment.TrailerFragment;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    TrailerFragment trailerFragment;
    int id;

    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (trailerFragment == null) {
                    trailerFragment = new TrailerFragment();
                }
                return trailerFragment;
            case 1:
                return new CatsFragment();

            case 2:
                return new ProducerFragment();

            default:
                if (trailerFragment == null) {
                    trailerFragment = new TrailerFragment();
                }
                return trailerFragment;
        }

    }


    @Override
    public int getCount() {
        return 3;
    }

    public void setData(int id) {
        this.id = id;
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
