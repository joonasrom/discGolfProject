package com.example.discGolfProject.model;

import org.springframework.data.repository.CrudRepository;


//User repository
public interface UserRepository extends CrudRepository<User,Long> {
	User findByUsername(String username);
	}