package com.example.phim88.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.phim88.R;
import com.example.phim88.databinding.FragmentSearchBinding;
import com.example.phim88.view.activity.BaseActivity;
import com.example.phim88.view.activity.MainActivity;

public class SearchFragment extends BaseFragment{
    private static final String TAG = "SearchFragment";

    private FragmentSearchBinding binding;
    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);


        binding.searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                startActivity(intent);
                if(callback != null) callback.back();
            }
        });

        return binding.getRoot();
    }

    public interface Callback {
        void back();
    }
}
