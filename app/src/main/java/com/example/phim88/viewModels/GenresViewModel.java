package com.example.phim88.viewModels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.phim88.BR;
import com.example.phim88.api.ApiGenre;
import com.example.phim88.model.ListGenre;

import java.util.List;

public class GenresViewModel extends BaseObservable {
    List<ListGenre.Content> genres;
    private ApiGenre apiGenre;

    public List<ListGenre.Content> getGenres() {
        return genres;
    }

    public void setGenres(List<ListGenre.Content> genres) {
        this.genres = genres;
    }

    public class Content extends BaseObservable {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Bindable
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            notifyPropertyChanged(BR.name);
        }
    }
}
