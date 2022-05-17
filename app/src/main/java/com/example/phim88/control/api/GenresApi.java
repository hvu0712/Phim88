package com.example.phim88.control.api;

import com.example.phim88.model.genre.GenreResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GenresApi {
    // 3/movie/popular 3/genre/movie/list
    @GET("3/genre/movie/list")
    Call<GenreResponse> getGenres(@Query("api_key") String key, @Query("language") String language);
}
