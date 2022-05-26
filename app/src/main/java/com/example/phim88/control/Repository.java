package com.example.phim88.control;

import com.example.phim88.control.api.DetailApi;
import com.example.phim88.control.api.GenresApi;
import com.example.phim88.control.api.PopularApi;
import com.example.phim88.control.api.SearchApi;
import com.example.phim88.control.api.UpcomingApi;
import com.example.phim88.model.detail.DetailResponse;
import com.example.phim88.model.genre.GenreResponse;
import com.example.phim88.model.popular.PopularResponse;
import com.example.phim88.model.upcoming.UpcomingResponse;
import com.example.phim88.util.Const;
import com.example.phim88.view.fragment.SearchFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.rxjava3.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private GenresApi genresApi;
    private PopularApi popularApi;
    private UpcomingApi upcomingApi;
    private SearchApi searchApi;
    private DetailApi detailApi;

    private Retrofit requestTheMovieDb;

    public Repository(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        requestTheMovieDb = new Retrofit.Builder()
                .baseUrl(Const.info.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        genresApi = requestTheMovieDb.create(GenresApi.class);
        popularApi = requestTheMovieDb.create(PopularApi.class);
        upcomingApi = requestTheMovieDb.create(UpcomingApi.class);
        searchApi = requestTheMovieDb.create(SearchApi.class);
        detailApi = requestTheMovieDb.create(DetailApi.class);
    }

    public void callApi(RequestCallback callback){
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

    public void callPopular(RequestCallback callback){
        Call<PopularResponse> call = popularApi.getPopular(Const.info.key, Const.info.language, 1);
        call.enqueue(new Callback<PopularResponse>() {
            @Override
            public void onResponse(Call<PopularResponse> call, Response<PopularResponse> response) {
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<PopularResponse> call, Throwable t) {
                callback.fail(t.getMessage());
            }
        });
    }

    public void callUpcoming(RequestCallback callback){
        Call<UpcomingResponse> call = upcomingApi.getUpComing(Const.info.key, Const.info.language, 1);
        call.enqueue(new Callback<UpcomingResponse>() {
            @Override
            public void onResponse(Call<UpcomingResponse> call, Response<UpcomingResponse> response) {
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<UpcomingResponse> call, Throwable t) {
                callback.fail(t.getMessage());
            }
        });
    }

    public void callSearch(RequestCallback callback, String query){
        Call<PopularResponse> call = searchApi.getSearch(Const.info.key, Const.info.language, query, 1, false);
        call.enqueue(new Callback<PopularResponse>() {
            @Override
            public void onResponse(Call<PopularResponse> call, Response<PopularResponse> response) {
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<PopularResponse> call, Throwable t) {
                callback.fail(t.getMessage());
            }
        });
    }

    public void callDetail(RequestCallback callback){
        Call<DetailResponse> call = detailApi.getDetail(639933,Const.info.key, Const.info.language);
        call.enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                callback.fail(t.getMessage());
            }
        });
    }

    public interface RequestCallback{
        void success(Object object);

        void fail(String msg);
    }
}
