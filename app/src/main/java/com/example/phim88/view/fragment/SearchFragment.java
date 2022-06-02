package com.example.phim88.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;

import com.example.phim88.R;
import com.example.phim88.databinding.FragmentSearchBinding;

import io.reactivex.rxjava3.subjects.PublishSubject;

public class SearchFragment extends BaseFragment {
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

        PublishSubject<String> subject = PublishSubject.create();


        binding.searchViewMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e(TAG, "onQueryTextSubmit: " + query);

                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty() == false) {
                    subject.onNext(newText);
                }
                return true;
            }
        });

        binding.searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) callback.back();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: ");
    }

    public interface Callback {
        void back();
    }
}
