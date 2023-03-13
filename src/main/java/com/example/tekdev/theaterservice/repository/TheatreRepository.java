package com.example.tekdev.theaterservice.repository;

import com.example.tekdev.theaterservice.models.Theatre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository  extends MongoRepository<Theatre,String> {

}
