package com.example.discGolfProject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.discGolfProject.model.Round;
import com.example.discGolfProject.model.RoundRepository;
import com.example.discGolfProject.model.Track;
import com.example.discGolfProject.model.TrackRepository;
import com.example.discGolfProject.model.User;
import com.example.discGolfProject.model.UserRepository;

@SpringBootApplication
public class DiscGolfApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscGolfApplication.class, args);
	}

	@Bean
	public CommandLineRunner categoryDemo(TrackRepository trepository, RoundRepository rrepository, UserRepository urepository) {
		return (args) -> {
			
			trepository.deleteAll();
			rrepository.deleteAll();
			urepository.deleteAll();

			Track t1 = new Track("Tuusulan urheilupuisto", 63);
			Track t2 = new Track("Kellokosken frisbeegolf", 58);
			Track t3 = new Track("Keinukallio", 70);
			Track t4 = new Track("Sodankylän frisbeegolf", 27);

			trepository.save(t1);
			trepository.save(t2);
			trepository.save(t3);
			trepository.save(t4);

			Round r1 = new Round("Joonas Romppanen", "20.08.2020 pelattu kierros porukalla", 72, trepository.findByName("Keinukallio").get(0));
			Round r2 = new Round("Matti Meikäläinen", "22.08.2020 pelattu kierros porukalla", 70, trepository.findByName("Keinukallio").get(0));
			rrepository.save(r1);
			rrepository.save(r2);
			
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "user@dgproject.fi");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin@dgproject.fi");
			urepository.save(user1);
			urepository.save(user2);
			

		};

	}

}
