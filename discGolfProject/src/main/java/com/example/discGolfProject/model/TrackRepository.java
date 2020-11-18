package com.example.discGolfProject.model;

import java.util.List;



import org.springframework.data.repository.CrudRepository;

//Track repository
public interface TrackRepository extends CrudRepository<Track, Long> {

    List<Track> findByName(String name);
    
}