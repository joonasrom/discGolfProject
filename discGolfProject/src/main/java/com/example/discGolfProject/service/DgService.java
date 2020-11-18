package com.example.discGolfProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.discGolfProject.model.Round;
import com.example.discGolfProject.model.RoundRepository;

//Service for search
	@Service
	public class DgService {
	    @Autowired
	    private RoundRepository r;
	     
	    public List<Round> findBySearchword(String searchword) {
	    	return r.findBySearchword(searchword);
	    }
	    
	}
	

