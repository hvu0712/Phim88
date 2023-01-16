package com.example.phim88.control.api;

import com.example.phim88.model.video.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VideoApi {
    @GET("3/movie/{movie_id}/videos")
    Call<VideoResponse> getVideos(@Path("movie_id") int movie_id,
                                  @Query("api_key") String api_key,
                                  @Query("language") String language);
}
