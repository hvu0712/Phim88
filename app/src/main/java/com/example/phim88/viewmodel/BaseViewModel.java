package com.example.phim88.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.example.phim88.control.Repository;
import com.google.gson.Gson;

public class BaseViewModel extends AndroidViewModel{
    private static final String TAG = BaseViewModel.class.getSimpleName();

    protected Gson gson;
    protected Repository repository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        gson = new Gson();
        repository = new Repository();
    }

}
