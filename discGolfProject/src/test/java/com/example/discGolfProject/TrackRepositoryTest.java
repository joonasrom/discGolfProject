package com.example.discGolfProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.discGolfProject.model.Track;
import com.example.discGolfProject.model.TrackRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TrackRepositoryTest {
	@Autowired
	private TrackRepository trepository;
	
	@Test
	public void findByName() {
		List<Track> trackList = trepository.findByName("Keinukallio");
		
		assertThat(trackList).hasSize(1);
		assertThat(trackList.get(0).getName()).isEqualTo("Keinukallio");
	}
	
	
	@Test
	public void deleteTrack() {
		trepository.delete(trepository.findByName("Keinukallio").get(0));
		List<Track> trackList = trepository.findByName("Keinukallio");
		assertThat(trackList).hasSize(0);
	}
	
	@Test
	public void createTrack() {
		Track track = new Track("Lapinjärven frisbeegolf", 27);
		trepository.save(track);
		assertThat(track.getTrackid()).isNotNull();
		
	}
}