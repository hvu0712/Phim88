package com.example.phim88.control.rest;

import androidx.lifecycle.MutableLiveData;

import com.example.phim88.control.api.ApiGenre;
import com.example.phim88.control.rest.request.MyRequest;
import com.example.phim88.model.ListGenre;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private MutableLiveData<ListGenre> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<ListGenre> getGenresListObserver() {
        return mutableLiveData;
    }

    public void callApi() {

        ApiGenre apiGenre = MyRequest.MyRequest().create(ApiGenre.class);
        Call<ListGenre> call = apiGenre.genre1();
        call.enqueue(new Callback<ListGenre>() {
            @Override
            public void onResponse(Call<ListGenre> call, Response<ListGenre> response) {
                mutableLiveData.postValue(response.body());
            }
            @Override
            public void onFailure(Call<ListGenre> call, Throwable t) {

            }
        });

//        ApiGenre.API_GENRE.genre1().enqueue(new Callback<ListGenre>() {
//            @Override
//            public void onResponse(Call<ListGenre> call, Response<ListGenre> response) {
//                mutableLiveData.postValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<ListGenre> call, Throwable t) {
//            }
//        });
    }

}
