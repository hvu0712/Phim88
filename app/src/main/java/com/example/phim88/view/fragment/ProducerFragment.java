package com.example.phim88.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.phim88.R;
import com.example.phim88.databinding.FragmentProducerBinding;

public class ProducerFragment extends Fragment {

    private FragmentProducerBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProducerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
