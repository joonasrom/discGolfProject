package com.example.discGolfProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

import javax.persistence.CascadeType;

@Entity
public class Track {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long trackid;
	private String name;
	private long trackPerfectScore;
	
	public long getTrackid() {
		return trackid;
	}

	public void setTrackid(long trackid) {
		this.trackid = trackid;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "track")
	private List<Round> rounds;

	public Track() {
	}

	public Track(String name, long trackPerfectScore) {

		super();
		this.name = name;
		this.trackPerfectScore = trackPerfectScore;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTrackPerfectScore() {
		return trackPerfectScore;
	}

	public void setTrackPerfectScore(long trackPerfectScore) {
		this.trackPerfectScore = trackPerfectScore;
	}

}
