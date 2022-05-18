package com.example.phim88.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.phim88.R;
import com.example.phim88.databinding.FragmentSearchBinding;

public class SearchFragment extends BaseFragment {

    private static final String TAG = "SearchFragment";
    FragmentSearchBinding binding;
    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);


        binding.searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBack != null) {
                    callBack.back();
                }
            }
        });

        return binding.getRoot();
    }

    public interface CallBack {
        void back();
    }
}
