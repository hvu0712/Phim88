package com.example.phim88.model;

import com.example.phim88.model.popular.Popular;
import com.example.phim88.model.upcoming.Upcoming;

import java.util.List;

public class Category {

    private String nameCategory;
    private List<Popular> movies;
    private List<Upcoming> upcomings;



    public void setUpcomings(List<Upcoming> upcomings, String nameCategory) {
        this.upcomings = upcomings;
    }

    public void setPopulars(List<Popular> movies, String nameCategory) {
        this.movies = movies;
        this.nameCategory = nameCategory;
        notify();
    }



    //    public Category(String nameCategory, List<Popular> movies) {
//        this.nameCategory = nameCategory;
//        this.movies = movies;
//    }

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
    public List<Upcoming> getUpcomings() {
        return upcomings;
    }
    public void setUpcomings(List<Upcoming> upcomings) {
        this.upcomings = upcomings;
    }

}
