package com.example.phim88.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.phim88.control.Repository;
import com.example.phim88.model.detail.Detail;
import com.example.phim88.model.detail.Genre;
import com.example.phim88.view.fragment.DetailFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailViewModel extends BaseViewModel{

    private MutableLiveData<List<Genre>> listGenre;
    private MutableLiveData<String> listDetail;
    private static final String TAG = "DetailViewModel";

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Genre>> getListGenre(){
        if (listGenre == null){
            listGenre = new MutableLiveData<>();
            listGenre.setValue(new ArrayList<>());
        }
        return listGenre;
    }

    public MutableLiveData<String> getListDetail(){
        if (listDetail == null){
            listDetail = new MutableLiveData<>();
        }
        return listDetail;
    }
    public void RequestListDetail(int movie_id){
        repository.callDetail(new Repository.RequestCallback() {
            @Override
            public void success(Object object) {
                if (object != null && object instanceof Detail){
                    List<Genre> genreList =  ((Detail) object).getGenres();
                    String detailList = ((Detail) object).getOverview();
                    DetailViewModel.this.listGenre.postValue(genreList);
                    DetailViewModel.this.listDetail.postValue(detailList);
                }
            }

            @Override
            public void fail(String msg) {
                Log.e(TAG, "fail: "+msg );
            }
        }, movie_id);
    }

}
