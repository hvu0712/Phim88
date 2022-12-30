package com.example.phim88.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.phim88.control.Repository;
import com.example.phim88.model.Video.Video;
import com.example.phim88.model.cast.Cast;
import com.example.phim88.model.cast.Credits;

import java.util.ArrayList;
import java.util.List;

public class CreditsViewModel extends BaseViewModel{
    private final MutableLiveData<Integer> id = new MutableLiveData<>();
    private MutableLiveData<List<Cast>> listCast;
    public CreditsViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Cast>> getListCast() {
        if (listCast == null){
            listCast = new MutableLiveData<>();
            listCast.setValue(new ArrayList<>());
        }
        return listCast;
    }

    public void requestCast(int movie_id){
        repository.callCredits(new Repository.RequestCallback() {
            @Override
            public void success(Object object) {
                if (object != null && object instanceof Credits){
                    List<Cast> casts = ((Credits) object).getCast();
                    if (casts != null && casts.size() > 0){
                        CreditsViewModel.this.listCast.postValue(casts);
                    }
                }
            }

            @Override
            public void fail(String msg) {

            }
        }, movie_id);
    }
}
