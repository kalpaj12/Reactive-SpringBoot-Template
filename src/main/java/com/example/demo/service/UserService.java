package com.example.demo.service;

import com.example.demo.persistence.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
	public Mono<User> saveUser(User user);

	public Mono<User> findByUserId(Long id);

	public Flux<User> findAll();

	public Mono<Void> deleteUser(User user);

	public Mono<User> updateUser(User user);

}