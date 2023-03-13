package com.example.tekdev.theaterservice.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
public class Theatre {
    @MongoId
    private String name;
    private String location;
    private int seats;
    private List<Show> shows;

    public Theatre(String name, String location, int seats) {
        this.name = name;
        this.location = location;
        this.seats = seats;
        shows = new ArrayList<>();
    }

    public List<Show> getShows() {
        Collections.sort(shows, new Comparator<Show>() {
            @Override
            public int compare(Show s1, Show s2) {
                return s1.getDateTime().compareTo(s2.getDateTime());
            }
        });
        return shows;
    }

    public void addShow(Show show) {
        shows.add(show);
    }
    public void deleteShow(Show show)
    {
        shows.remove(show);
    }
}
