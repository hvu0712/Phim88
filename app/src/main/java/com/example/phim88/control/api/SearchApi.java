package com.example.phim88.control.api;

import com.example.phim88.model.search.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchApi {
    @GET("3/search/movie")
    Call<SearchResponse> getSearch(@Query("api_key") String api_key,
                                   @Query("language") String language,
                                   @Query("query") String query,
                                   @Query("page") int page,
                                   @Query("include_adult") boolean include_adult);
}
