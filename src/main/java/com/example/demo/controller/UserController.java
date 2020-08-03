package com.example.demo.controller;

import com.example.demo.persistence.model.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/create")
	public Mono<ResponseEntity<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user).map(u -> ResponseEntity.ok().body(u))
				.defaultIfEmpty(ResponseEntity.badRequest().build());
	}

	@GetMapping(value = "/view/{id}")
	public Mono<ResponseEntity<User>> getUser(@PathVariable("id") Long id) {
		return userService.findByUserId(id).map(u -> ResponseEntity.ok().body(u))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping(value = "/viewAll")
	public Flux<User> getAllUsers() {
		return userService.findAll();
	}

	// @Todo: Fix the below

	// @DeleteMapping(value = "/delete/{id}")
	// public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
	// try {
	// Optional<User> userOpt = userService.findByUserId(id);
	// User user = userOpt.get();
	// if (user != null) {
	// userService.deleteUser(user);
	// return ResponseEntity.ok().body(user);
	// } else {
	// return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	// }
	// } catch (Exception e) {
	// return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	// }
	// }

	// @PutMapping(value = "/update")
	// public ResponseEntity<User> updateUser(@RequestBody User user) {
	// try {
	// Optional<User> userOpt = userService.findByUserId(user.getUserId());
	// User toUpdate = userOpt.get();
	// if (toUpdate != null) {
	// return ResponseEntity.ok(userService.updateUser(user));
	// } else {
	// return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	// }
	// } catch (Exception e) {
	// return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	// }
	// }

}