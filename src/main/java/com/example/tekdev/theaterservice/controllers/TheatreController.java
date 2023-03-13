package com.example.tekdev.theaterservice.controllers;

import com.example.tekdev.theaterservice.models.Movie;
import com.example.tekdev.theaterservice.models.Show;
import com.example.tekdev.theaterservice.services.TheatreService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/{theatreName}")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @GetMapping("/getShows")
    public List<Show> getShows(@PathVariable String theatreName) {
        return theatreService.getTheatreShows(theatreName);
    }

    @PostMapping("/createShow")
    public void createShow(@PathVariable String theatreName, @RequestBody ShowDTO showDTO) {
        String url = "http://localhost:8081/" + showDTO.movieName;
        Movie movie = new RestTemplate().getForObject(url, Movie.class);
        LocalDateTime dateTime = LocalDateTime.of(showDTO.year,
                showDTO.month,
                showDTO.day,
                showDTO.hours,
                showDTO.minutes);
        theatreService.addShowToTheatre(theatreName, movie, dateTime);
    }

    @DeleteMapping("/deleteShow")
    public void deleteShow(@PathVariable String theatreName, @RequestBody StringUnwrapper stringUnwrapper) {
        System.out.println("Inside delete show ");
        theatreService.deleteShowFromTheatre(theatreName, stringUnwrapper.string);
    }

    @PostMapping("/bookShow")
    public void makeReservation(@PathVariable String theatreName, @RequestBody ReservationDTO reservationDTO) {
        System.out.println("inside book show method in theatre service");
        System.out.println(reservationDTO);
        theatreService.bookAShow(
                theatreName,
                reservationDTO.showId,
                reservationDTO.userId,
                reservationDTO.seats);

    }

    @Data
    static class ReservationDTO {
        private String userId;
        private String showId;
        private int seats;
    }

    @Data
    static class StringUnwrapper {
        private String string;
    }

    @Data
    static class ShowDTO {
        private String movieName;
        private int day;
        private int month;
        private int year;
        private int hours;
        private int minutes;

        public ShowDTO(String movieName, int day, int month, int year, int hours, int minutes) {
            this.movieName = movieName;
            this.day = day;
            this.month = month;
            this.year = year;
            this.hours = hours;
            this.minutes = minutes;
        }
    }

}
