package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.persistence.model.User;

public interface UserService {
	public User saveUser(User user);

	public Optional<User> findByUserId(Long id);

	public List<User> findAll();

}