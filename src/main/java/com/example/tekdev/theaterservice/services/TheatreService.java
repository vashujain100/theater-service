package com.example.tekdev.theaterservice.services;

import com.example.tekdev.theaterservice.models.Movie;
import com.example.tekdev.theaterservice.models.Show;
import com.example.tekdev.theaterservice.models.Theatre;
import com.example.tekdev.theaterservice.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;

    public List<Theatre> getAllTheatre() {
        return theatreRepository.findAll();
    }

    public void addTheatre(Theatre theatre) {
        theatreRepository.save(theatre);
        System.out.println(theatre.getName() + " added successfully!! ");
    }

    public void deleteTheatre(String theatreName) {
        theatreRepository.deleteById(theatreName);
        System.out.println(theatreName + " deleted successfully..");
    }
    public Theatre getTheatreByName(String theatreName)
    {
        Optional<Theatre> theatre = theatreRepository.findById(theatreName);
        if(theatre.isPresent())
            return theatre.get();
        else
            return new Theatre("default","default",0);
    }
    public List<Show> getTheatreShows(String theatreName) {
        Optional<Theatre> theatre = theatreRepository.findById(theatreName);
        if (theatre.isPresent()) {
            return theatre.get().getShows();
        } else {
            return new ArrayList<>();
        }
    }
    public void addShowToTheatre(String theatreName, Movie movie, LocalDateTime dateTime)
    {
      Optional<Theatre> theatre =  theatreRepository.findById((theatreName));
      if(theatre.isPresent())
      {
          System.out.println(movie.getMovieName());
          System.out.println(dateTime);
          System.out.println(theatre.get().getName());
          System.out.println(theatre.get().getSeats());
          Show show = new Show(movie,dateTime,theatre.get().getName(),theatre.get().getSeats());
          theatre.get().addShow(show);
          theatreRepository.save(theatre.get());
      }
      else {
        //  new IllegalStateException(HttpStatus.BAD_REQUEST)
      }
    }

    public List<Show> getTheatresShowingTheMovie(String movieName) {
      List<Show> shows = new ArrayList<>();
      theatreRepository.findAll().forEach(theatre ->
              theatre.getShows().forEach(
              show -> {
                  if(show.getMovie().getMovieName().equals(movieName))
              {
                  shows.add(show);
              };}
      ));
      return shows;
    }

    public void bookAShow(String theatreId, String showId,String userEmail, int seats)
    {
        //  Show show;
        Theatre theatre = theatreRepository.findById(theatreId).get();
        theatre.getShows().forEach(show -> {
            if(show.getShowId().equals(showId))
            {
                show.makeBooking(userEmail,seats);

                theatreRepository.save(theatre);
            }
        });
      //  show.makeBooking(userEmail,seats);
    }

    public void deleteShowFromTheatre(String theatreName, String showId) {
        Theatre theatre = theatreRepository.findById(theatreName).get();
        theatre.getShows().forEach(show -> {
            if(show.getShowId().equals(showId))
            {
                theatre.deleteShow(show);
                theatreRepository.save(theatre);
            }
        });
    }
}
