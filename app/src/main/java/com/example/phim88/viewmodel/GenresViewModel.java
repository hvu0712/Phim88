package com.example.phim88.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.phim88.control.Repository;
import com.example.phim88.model.genre.Genre;
import com.example.phim88.model.genre.GenreResponse;

import java.util.ArrayList;
import java.util.List;

public class GenresViewModel extends BaseViewModel {

    private static final String TAG = GenresViewModel.class.getSimpleName();

    private MutableLiveData<List<Genre>> genres;

    public GenresViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Genre>> getGenres() {
        if (genres == null) {
            genres = new MutableLiveData<>();
            genres.setValue(new ArrayList<>());
        }
        return genres;
    }

    public void requestGenres() {
        repository.callApi(new Repository.RequestCallback() {
            @Override
            public void success(Object object) {
                if (object != null && object instanceof GenreResponse) {
                    List<Genre> genres = ((GenreResponse) object).getGenres();
                    if (genres != null && genres.size() > 0) {
                        GenresViewModel.this.genres.postValue(genres);
                    }
                }
            }

            @Override
            public void fail(String msg) {
                // TODO
            }
        });
    }
}
