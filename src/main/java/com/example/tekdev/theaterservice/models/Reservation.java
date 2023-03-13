package com.example.tekdev.theaterservice.models;

import lombok.Data;

@Data
public class Reservation {
    private Show show;
    private int numberOfSeats;

    private Theatre theatre;

    public Reservation(Show show, int numberOfSeats, Theatre theatre) {
        this.show = show;
        this.numberOfSeats = numberOfSeats;
        this.theatre = theatre;
    }
}
