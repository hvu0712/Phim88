package com.example.phim88.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Integer> mData = new MutableLiveData<>();

    public LiveData<Integer> getmData() {
        return mData;
    }

    public void setData(Integer data){
        SharedViewModel.this.mData.setValue(data);
    }
}
