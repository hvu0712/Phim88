package com.example.phim88.control;

import android.content.Context;

import com.example.phim88.control.api.CreditsApi;
import com.example.phim88.control.api.DetailApi;
import com.example.phim88.control.api.GenresApi;
import com.example.phim88.control.api.PopularApi;
import com.example.phim88.control.api.SearchApi;
import com.example.phim88.control.api.UpcomingApi;
import com.example.phim88.control.api.VideoApi;
import com.example.phim88.model.video.VideoResponse;
import com.example.phim88.model.cast.Credits;
import com.example.phim88.model.detail.Detail;
import com.example.phim88.model.genre.GenreResponse;
import com.example.phim88.model.popular.PopularResponse;
import com.example.phim88.model.search.SearchResponse;
import com.example.phim88.model.upcoming.UpcomingResponse;
import com.example.phim88.util.Const;
import com.example.phim88.view.fragment.DetailFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final String TAG = "Repository";
    private final GenresApi genresApi;
    private final PopularApi popularApi;
    private final UpcomingApi upcomingApi;
    private final SearchApi searchApi;
    private final DetailApi detailApi;
    private final VideoApi videoApi;
    private final CreditsApi creditsApi;
    private final Retrofit requestTheMovieDb;
    private DetailFragment detailFragment;
    private Context context;

    public Repository() {
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
        videoApi = requestTheMovieDb.create(VideoApi.class);
        creditsApi = requestTheMovieDb.create(CreditsApi.class);
    }

    public void callApi(RequestCallback callback) {
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

    public void callPopular(RequestCallback callback) {
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

    public void callUpcoming(RequestCallback callback) {
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

    public void callSearch(RequestCallback callback, String query) {
        Call<SearchResponse> call = searchApi.getSearch(Const.info.key, Const.info.language, query, 1, false);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                callback.fail(t.getMessage());
            }
        });
    }

    public void callDetail(RequestCallback callback, int movie_id) {
        detailFragment = new DetailFragment();
//        Log.e(TAG, "callDetail: "+detailFragment.getArguments().getInt("id"));
        Call<Detail> call = detailApi.getDetail(movie_id, Const.info.key, Const.info.language);
        call.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {
                callback.fail(t.getMessage());
            }
        });
    }

    public void callVideo(RequestCallback callback, int movie_id) {
        Call<VideoResponse> call = videoApi.getVideos(movie_id, Const.info.key, Const.info.language);
        call.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                callback.fail(t.getMessage());
            }
        });
    }

    public void callCredits(RequestCallback callback, int movie_id){
        Call<Credits> call = creditsApi.getCredits(movie_id, Const.info.key, Const.info.language);
        call.enqueue(new Callback<Credits>() {
            @Override
            public void onResponse(Call<Credits> call, Response<Credits> response) {
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<Credits> call, Throwable t) {
                callback.fail(t.getMessage());
            }
        });
    }

    public interface RequestCallback {
        void success(Object object);

        void fail(String msg);
    }
}
