package com.example.discGolfProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.discGolfProject.model.Round;
import com.example.discGolfProject.model.RoundRepository;
import com.example.discGolfProject.model.TrackRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DgRepositoryTest {

	@Autowired
	private RoundRepository rrepository;

	@Autowired
	private TrackRepository trepository;

	@Test
	public void createNewRound() {
		Round round = new Round("Jopi Rope", "12.04.2020 pelattu kierros porukalla", 32, trepository.findByName("Keinukallio").get(0));
		rrepository.save(round);
		assertThat(round.getId()).isNotNull();
	}

	@Test
	public void deleteRound() {
		rrepository.delete(rrepository.findByStory("20.08.2020 pelattu kierros porukalla").get(0));
		List<Round> roundlist = rrepository.findByStory("20.08.2020 pelattu kierros porukalla");
		assertThat(roundlist).hasSize(0);

	}

	@Test
	public void findByRoundStory() {
		List<Round> roundList = rrepository.findByStory("20.08.2020 pelattu kierros porukalla");

		assertThat(roundList).hasSize(1);
		assertThat(roundList.get(0).getPlayer()).isEqualTo("Joonas Romppanen");
	}
}