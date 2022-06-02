package com.example.phim88.control.api;

import com.example.phim88.model.detail.Detail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DetailApi {
    @GET("3/movie/{movie_id}")
    Call<Detail> getDetail(@Path("movie_id") Integer movie_id,
                           @Query("api_key") String api_key,
                           @Query("language") String language
    );
}
