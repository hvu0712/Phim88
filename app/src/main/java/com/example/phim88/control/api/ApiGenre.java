package com.example.phim88.control.api;

import com.example.phim88.model.ListGenre;
import com.example.phim88.util.Const;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiGenre {
    // API: https://api.themoviedb.org/3/genre/movie/list?api_key=c5bc51188f077d87779efbc157e53c08&language=en-US
//    Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//            .create();
//    ApiGenre API_GENRE = new Retrofit.Builder()
//            .baseUrl(Const.info.baseUrl)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(ApiGenre.class);
//    @GET("3/genre/movie/list")
//    Call<ListGenre> genre(@Query("api_key") String api_key,
//                          @Query("language") String language);

    @GET(Const.info.genresMovie)
    Call<ListGenre> genre1();
}
