package com.example.phim88.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.phim88.model.popular.Popular;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends BaseViewModel{

    private MutableLiveData<List<Popular>> mlist;

    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Popular>> getListSearch(){
        if (mlist == null){
            mlist = new MutableLiveData<>();
            mlist.setValue(new ArrayList<>());
        }
        return mlist;
    }

}
