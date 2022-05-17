package com.example.phim88.model;

public class Movie {

    private int imgMovie;
    private String nameMovie;

    public Movie(int imgMovie, String nameMovie) {
        this.imgMovie = imgMovie;
        this.nameMovie = nameMovie;
    }

    public int getImgMovie() {
        return imgMovie;
    }

    public void setImgMovie(int imgMovie) {
        this.imgMovie = imgMovie;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }
}
