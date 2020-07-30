package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}