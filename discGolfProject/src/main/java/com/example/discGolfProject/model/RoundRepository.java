package com.example.discGolfProject.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoundRepository extends CrudRepository<Round, Long> {

    List<Round> findByStory(String story);
    
}