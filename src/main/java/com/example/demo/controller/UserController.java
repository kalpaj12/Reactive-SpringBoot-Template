package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.persistence.model.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v1/user")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/create")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		try {
			return ResponseEntity.ok().body(userService.saveUser(user));
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<User>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping(value = "/view/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok().body(userService.findByUserId(id).orElse(new User()));
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/viewAll")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			return ResponseEntity.ok().body(userService.findAll());
		} catch (Exception e) {
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
	}

}