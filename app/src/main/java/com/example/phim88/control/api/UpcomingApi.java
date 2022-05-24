package com.example.phim88.control.api;

import com.example.phim88.model.upcoming.UpcomingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpcomingApi {
    @GET("3/movie/upcoming")
    Call<UpcomingResponse> getUpComing(@Query("api_key") String key,
                                     @Query("language") String language,
                                     @Query("page") int page);
}
