package com.example.phim88.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.phim88.control.Repository;
import com.example.phim88.model.discover.Discover;
import com.example.phim88.model.discover.Result;

import java.util.ArrayList;
import java.util.List;

public class DiscoverViewModel extends BaseViewModel{
    private MutableLiveData<List<Result>> mutableLiveData;
    public DiscoverViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Result>> getMutableLiveData() {
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
            mutableLiveData.setValue(new ArrayList<>());
        }
        return mutableLiveData;
    }
    public void requestDiscover(int with_genres){
        repository.callDiscover(new Repository.RequestCallback() {
            @Override
            public void success(Object object) {
                if (object != null && object instanceof Discover){
                    List<Result> results = ((Discover) object).getResults();
                    if (results != null && results.size() > 0){
                        DiscoverViewModel.this.mutableLiveData.postValue(results);
                    }
                }
            }

            @Override
            public void fail(String msg) {
                Log.e("TAG", "fail: "+msg);
            }
        }, with_genres);
    }
}
