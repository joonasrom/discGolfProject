package com.example.discGolfProject;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.discGolfProject.model.UserRepository;
import com.example.discGolfProject.web.DgController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DgApplicationTests {
	
	@Autowired
	private DgController dgController;

	@Autowired
	private UserRepository userRepo;
	
	@Test
	void contextLoads() throws Exception {
		assertThat(dgController).isNotNull();
		assertThat(userRepo).isNotNull();
	}

}