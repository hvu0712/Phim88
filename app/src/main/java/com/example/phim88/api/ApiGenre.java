package com.example.phim88.api;

import com.example.phim88.model.ListGenre;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiGenre {
    // API: https://api.themoviedb.org/3/genre/movie/list?api_key=c5bc51188f077d87779efbc157e53c08&language=en-US
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiGenre API_GENRE = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiGenre.class);
    @GET("3/genre/movie/list")
    Call<ListGenre> genre(@Query("api_key") String api_key,
                          @Query("language") String language);
}
