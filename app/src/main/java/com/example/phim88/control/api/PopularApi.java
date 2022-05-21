package com.example.phim88.control.api;

import com.example.phim88.model.popular.PopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PopularApi {
    @GET("3/movie/popular")
    Call<PopularResponse> getPopular(@Query("api_key") String key,
                                     @Query("language") String language,
                                     @Query("page") int page);
}
