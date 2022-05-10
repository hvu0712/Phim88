package com.example.phim88.model;

public class Upcoming {
    private String title;
    private int poster_path;

    public Upcoming(String title, int poster_path) {
        this.title = title;
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(int poster_path) {
        this.poster_path = poster_path;
    }
}
