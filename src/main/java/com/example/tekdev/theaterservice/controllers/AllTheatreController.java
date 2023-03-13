package com.example.tekdev.theaterservice.controllers;

import com.example.tekdev.theaterservice.models.MovieList;
import com.example.tekdev.theaterservice.models.Show;
import com.example.tekdev.theaterservice.models.Theatre;
import com.example.tekdev.theaterservice.services.TheatreService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class AllTheatreController {
    @Autowired
    private TheatreService theatreService;

    @RequestMapping("/getTheatres")
    public List<Theatre> getTheatre() {
        return theatreService.getAllTheatre();
    }

    @PostMapping("/addTheatre")
    public void addTheatre(@RequestBody Theatre newTheatre) {
        theatreService.addTheatre(newTheatre);
    }

    @DeleteMapping("/deleteTheatre")
    public void deleteTheatre(@RequestBody TheatreName theatreName) {
        theatreService.deleteTheatre(theatreName.getTheatreName());
    }

    @GetMapping("/getMovies")
    public MovieList getMovies() {
        String url = "http://localhost:8081/all";
        return new RestTemplate().getForObject(url, MovieList.class);
    }

    @GetMapping("/getTheatresForMovie/{movieName}")
    public List<Show> getTheatresShowingTheMovie(@PathVariable String movieName) {
        return theatreService.getTheatresShowingTheMovie(movieName);
    }

    @Data
    static class TheatreName {
        private String theatreName;
    }

}
