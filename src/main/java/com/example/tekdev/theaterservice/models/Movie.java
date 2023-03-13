package com.example.tekdev.theaterservice.models;

//import jakarta.persistence.Id;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
public class Movie {
    @MongoId
    private String movieName;
    private String movieDescription;
    private double movieRating;

    Movie(Builder movieBuilder) {
        movieName = movieBuilder.movieName;
        movieDescription = movieBuilder.movieDescription;
        movieRating = movieBuilder.movieRating;
    }

public Movie()
{}
    public Movie(String movieName, String movieDescription, double movieRating) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieRating = movieRating;
    }

    public static class Builder {
        //required fields
        private String movieName;
        //optional fields
        private String movieDescription = "";
        private double movieRating = 0;

        public Builder(String movieName) {
            this.movieName = movieName;
        }

        public Builder description(String movieDescription) {
            this.movieDescription = movieDescription;
            return this;
        }

        public Builder rating(double movieRating) {
            this.movieRating = movieRating;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

}
