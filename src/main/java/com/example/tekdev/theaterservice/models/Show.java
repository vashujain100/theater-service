package com.example.tekdev.theaterservice.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class Show {

    @MongoId
    private String showId;
    private Movie movie;
    private LocalDateTime dateTime;
    private String theatreName;

    private int totalNumberOfSeats;
    private int totalSeatsBooked;
    private Map<String, Integer> bookings;


    public Show(Movie movie, LocalDateTime dateTime, String theatreName, int totalNumberOfSeats) {
        showId = movie.getMovieName() + " " + theatreName + " " + dateTime.toString();
        this.movie = movie;
        this.dateTime = dateTime;
        this.theatreName = theatreName;
        this.totalNumberOfSeats = totalNumberOfSeats;
        totalSeatsBooked = 0;
        bookings = new HashMap<>();
    }

    public Movie getMovie() {
        return movie;
    }

    public void makeBooking(String userEmail, int numberOfSeats) {
        if (numberOfSeats < totalNumberOfSeats - totalSeatsBooked) {
            System.out.println("Bookings made successfully");
        } else if (numberOfSeats > totalNumberOfSeats - totalSeatsBooked) {
            System.out.println("only " + (totalNumberOfSeats - totalSeatsBooked) + " seats can be booked");
            numberOfSeats = totalNumberOfSeats - totalSeatsBooked;
        } else {
            System.out.println("No bookings can be made");
            return;
        }
        bookings.put(userEmail, numberOfSeats);
        totalSeatsBooked += numberOfSeats;
    }
}
