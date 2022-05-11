package com.example.phim88.model;

import java.util.List;

public class ListGenre {
    private List<Content> genres;

    public void setGenres(List<Content> genres) {
        this.genres = genres;
    }

    public List<Content> getGenres() {
        return genres;
    }

    public class Content{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
