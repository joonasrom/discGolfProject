package com.example.discGolfProject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.discGolfProject.model.User;
import com.example.discGolfProject.model.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository urepository;

	@Test
	public void createUser() {
		User user = new User("Mr.Bean", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER",
				"beanie@bookstore.fi");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
	}

	@Test
	public void findByUserName() {
		User user = urepository.findByUsername("admin");
		assertThat(user).isNotNull();
		assertThat(user.getRole()).isEqualTo("ADMIN");
	}

	@Test
	public void deleteUser() {
		urepository.delete(urepository.findByUsername("user"));
		User user = urepository.findByUsername("user");
		assertThat(user).isNull();
	}
}