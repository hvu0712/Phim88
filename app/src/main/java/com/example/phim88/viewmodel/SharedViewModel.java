package com.example.phim88.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phim88.model.genre.Genre;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Integer> mData = new MutableLiveData<>();
    private final MutableLiveData<Genre> genreMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Genre> getGenreMutableLiveData() {
        return genreMutableLiveData;
    }

    public LiveData<Integer> getmData() {
        return mData;
    }

    public void setData(Integer data){
        SharedViewModel.this.mData.setValue(data);
    }
    public void setGenreMutableLiveData(Genre genre){
        SharedViewModel.this.genreMutableLiveData.setValue(genre);
    }
}
