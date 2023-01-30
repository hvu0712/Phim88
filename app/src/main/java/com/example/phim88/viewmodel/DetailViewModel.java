package com.example.phim88.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.phim88.control.Repository;
import com.example.phim88.model.detail.Detail;
import com.example.phim88.model.detail.Genre;
import com.example.phim88.model.detail.ProductionCompany;

import java.util.ArrayList;
import java.util.List;

public class DetailViewModel extends BaseViewModel {

    private static final String TAG = "DetailViewModel";
    private MutableLiveData<List<Genre>> listGenre;
    private MutableLiveData<String> listDetail;
    private MutableLiveData<List<ProductionCompany>> listProductionCompany;
    private MutableLiveData<Detail> liveData;

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Detail> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }

    public MutableLiveData<List<Genre>> getListGenre() {
        if (listGenre == null) {
            listGenre = new MutableLiveData<>();
            listGenre.setValue(new ArrayList<>());
        }
        return listGenre;
    }

    public MutableLiveData<List<ProductionCompany>> getListProductionCompany() {
        if (listProductionCompany == null) {
            listProductionCompany = new MutableLiveData<>();
            listProductionCompany.setValue(new ArrayList<>());
        }
        return listProductionCompany;
    }
    public MutableLiveData<String> getListDetail() {
        if (listDetail == null) {
            listDetail = new MutableLiveData<>();
        }
        return listDetail;
    }

    public void RequestListDetail(int movie_id) {
        repository.callDetail(new Repository.RequestCallback() {
            @Override
            public void success(Object object) {
                if (object != null && object instanceof Detail) {
//                    try {
//                        List<Genre> genreList = ((Detail) object).getGenres();
//                        String detailList = ((Detail) object).getOverview();
//                        List<ProductionCompany> productionCompanies = ((Detail) object).getProductionCompanies();
//                        Log.e(TAG, "success: "+productionCompanies.get(0).getName());
//                        DetailViewModel.this.listProductionCompany.postValue(productionCompanies);
//                        DetailViewModel.this.listGenre.postValue(genreList);
//                        DetailViewModel.this.listDetail.postValue(detailList);
//                    } catch (Exception e){
//
//                    }
                    Detail o = (Detail) object;
                    DetailViewModel.this.liveData.postValue(o);
                    Log.e(TAG, "success: "+o);
                }
            }

            @Override
            public void fail(String msg) {
                Log.e(TAG, "fail: " + msg);
            }
        }, movie_id);
    }

}
