package com.example.phim88.control.api;

import com.example.phim88.model.discover.Discover;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DiscoverApi {
    @GET("3/discover/movie")
    Call<Discover> getDiscover(@Query("api_key") String key ,@Query("language") String language, @Query("include_adult") boolean include_adult, @Query("include_video") boolean include_video, @Query("page") int page, @Query("with_genres") int with_genres);
}
