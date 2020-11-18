package com.example.discGolfProject.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoundRepository extends CrudRepository<Round, Long> {

    List<Round> findByStory(String story);
    
    List<Round> findByPlayer(String player);
    
    @Query(value = "SELECT * FROM Round r WHERE r.player LIKE %:searchword%", nativeQuery=true)
    List<Round>findBySearchword(@Param("searchword") String searchword);
}
