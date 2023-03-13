package com.example.tekdev.theaterservice.models;

import java.util.List;

public class MovieList {
    public List<Movie> movieList;

    public MovieList() {
    }

    public MovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
