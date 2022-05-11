package com.example.phim88.control.rest.request;

import com.example.phim88.util.Const;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRequest {
    public static Retrofit retrofit;
    public static Retrofit MyRequest(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Const.info.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }
}
