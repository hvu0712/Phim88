package com.example.phim88.control;

import com.example.phim88.control.api.GenresApi;
import com.example.phim88.model.genre.GenreResponse;
import com.example.phim88.util.Const;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private final GenresApi genresApi;

    private final Retrofit requestTheMovieDb;

    public Repository() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        requestTheMovieDb = new Retrofit.Builder()
                .baseUrl(Const.info.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        genresApi = requestTheMovieDb.create(GenresApi.class);

    }

    public void callApi(RequestCallback callback) {
        if (callback == null)
            return;

        Call<GenreResponse> call = genresApi.getGenres(Const.info.key, Const.info.language);
        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                callback.fail(t.getMessage());
            }
        });
    }

    public interface RequestCallback {
        void success(Object object);

        void fail(String msg);
    }
}
