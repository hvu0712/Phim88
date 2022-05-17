package com.example.phim88.model;

import java.util.List;

public class Category {

    private String nameCategory;
    private List<Movie> movies ;

    public Category(String nameCategory, List<Movie> movies) {
        this.nameCategory = nameCategory;
        this.movies = movies;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
