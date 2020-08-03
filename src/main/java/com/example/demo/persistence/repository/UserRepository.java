package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.User;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

}