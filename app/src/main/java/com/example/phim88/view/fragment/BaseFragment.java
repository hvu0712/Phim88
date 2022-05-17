package com.example.phim88.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.phim88.view.activity.MainActivity;

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void initToolBar(Toolbar toolbar){
        ((MainActivity) requireActivity() ).setSupportActionBar(toolbar);
        ((MainActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) requireActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


}
