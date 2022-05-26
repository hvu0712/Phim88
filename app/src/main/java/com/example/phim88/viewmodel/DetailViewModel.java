package com.example.phim88.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.phim88.control.Repository;
import com.example.phim88.model.detail.Detail;
import com.example.phim88.model.detail.DetailResponse;

import java.util.ArrayList;
import java.util.List;

public class DetailViewModel extends BaseViewModel{

    private MutableLiveData<List<Detail>> listDetail;
    private int movie_id = 639933;

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Detail>> getListDetail(){
        if (listDetail == null){
            listDetail = new MutableLiveData<>();
            listDetail.setValue(new ArrayList<>());
        }
        return listDetail;
    }

    public void RequestListDetail(){
        repository.callDetail(new Repository.RequestCallback() {
            @Override
            public void success(Object object) {
                if (object != null && object instanceof DetailResponse){
                    List<Detail> detailList = ((DetailResponse) object).getResults();
                    if (detailList != null && detailList.size() > 0){
                        DetailViewModel.this.listDetail.postValue(detailList);
                    }
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

}
