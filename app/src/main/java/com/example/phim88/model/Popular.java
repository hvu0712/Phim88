package com.example.phim88.model;

import java.util.List;

public class Popular {
    private int poster_path;
    private String title;

    public Popular(int poster_path, String title) {
        this.poster_path = poster_path;
        this.title = title;
    }

    public int getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(int poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
