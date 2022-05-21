package com.example.phim88.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.phim88.control.Repository;
import com.example.phim88.model.upcoming.Upcoming;

import java.util.ArrayList;
import java.util.List;

public class UpcomingViewModel extends BaseViewModel{
    private MutableLiveData<List<Upcoming>> listUpcoming;
    public UpcomingViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Upcoming>> getListUpcoming() {
        if (listUpcoming == null){
            listUpcoming = new MutableLiveData<>();
            listUpcoming.setValue(new ArrayList<>());
        }
        return listUpcoming;
    }

    public void RequestUpcoming(){
        repository.callUpcoming(new Repository.RequestCallback() {
            @Override
            public void success(Object object) {

            }

            @Override
            public void fail(String msg) {

            }
        });
    }
}
