package com.example.phim88.model.popular;

import com.example.phim88.model.popular.Popular;

import java.util.List;

public class Category {

    private String nameCategory;
    private List<Popular> movies ;

    public Category(String nameCategory, List<Popular> movies) {
        this.nameCategory = nameCategory;
        this.movies = movies;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Popular> getMovies() {
        return movies;
    }

    public void setMovies(List<Popular> movies) {
        this.movies = movies;
    }
}
