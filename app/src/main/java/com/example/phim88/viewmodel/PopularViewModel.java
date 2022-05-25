package com.example.phim88.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.phim88.control.Repository;
import com.example.phim88.model.Category;
import com.example.phim88.model.popular.Popular;
import com.example.phim88.model.popular.PopularResponse;

import java.util.ArrayList;
import java.util.List;

public class PopularViewModel extends BaseViewModel {
    private MutableLiveData<List<Popular>> listPopular;
    public List<Popular> movieListPopular = new ArrayList<>();
    private static final String TAG = "PopularViewModel";
    public List<Category> listCategory = new ArrayList<>();
    private Category category;


    public PopularViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Popular>> getListPopular() {
        if (listPopular == null) {
            listPopular = new MutableLiveData<>();
            listPopular.setValue(new ArrayList<>());
        }
        return listPopular;
    }

    public void requestPopular() {
        repository.callPopular(new Repository.RequestCallback() {
            @Override
            public void success(Object object) {
                if (object != null && object instanceof PopularResponse) {
                    List<Popular> listPopular = ((PopularResponse) object).getResults();
                    if (listPopular != null && listPopular.size() > 0) {
                        PopularViewModel.this.listPopular.postValue(listPopular);
                    }
                }
            }

            @Override
            public void fail(String msg) {
            }
        });
    }

}
