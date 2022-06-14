package com.example.phim88.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.phim88.control.Repository;
import com.example.phim88.model.popular.Popular;
import com.example.phim88.model.search.Result;
import com.example.phim88.model.search.SearchResponse;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends BaseViewModel {

    private MutableLiveData<List<Result>> mlist;

    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Result>> getListSearch() {
        if (mlist == null) {
            mlist = new MutableLiveData<>();
            mlist.setValue(new ArrayList<>());
        }
        return mlist;
    }

    public void requestSearch(String query){
        repository.callSearch(new Repository.RequestCallback() {
            @Override
            public void success(Object object) {
                if(object != null && object instanceof SearchResponse){
                    List<Result> resultList  = ((SearchResponse) object).getResults();
                    if (resultList != null && resultList.size() > 0){
                        SearchViewModel.this.mlist.postValue(resultList);
                    }
                }
            }

            @Override
            public void fail(String msg) {
                Log.e("TAG", "fail: "+msg);

            }
        }, query);
    }

}
