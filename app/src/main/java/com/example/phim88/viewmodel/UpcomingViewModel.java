package com.example.phim88.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.phim88.control.Repository;
import com.example.phim88.model.popular.PopularResponse;
import com.example.phim88.model.upcoming.Upcoming;
import com.example.phim88.model.upcoming.UpcomingResponse;

import java.util.ArrayList;
import java.util.List;

public class UpcomingViewModel extends BaseViewModel{
    private MutableLiveData<List<Upcoming>> listUpcoming;
    public UpcomingViewModel(@NonNull Application application) {
        super(application);
    }

    private static final String TAG = "UpcomingViewModel";
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
                if (object != null && object instanceof UpcomingResponse){
                    List<Upcoming> upcomingList = ((UpcomingResponse) object).getResults();
                    if (upcomingList != null && upcomingList.size() > 0){
                        UpcomingViewModel.this.listUpcoming.postValue(upcomingList);
                    }
                }
            }

            @Override
            public void fail(String msg) {
                Log.e(TAG, "fail: "+msg);
            }
        });
    }
}
