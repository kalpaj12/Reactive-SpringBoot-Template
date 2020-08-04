package com.example.demo.controller;

import com.example.demo.persistence.model.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
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

	@DeleteMapping(value = "/delete/{id}")
	public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") Long id) {
		return userService.findByUserId(id)
				.flatMap(dbUser -> userService.deleteUser(dbUser).then(Mono.just(ResponseEntity.ok().<Void>build())))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/update/{id}")
	public Mono<ResponseEntity<User>> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		return userService.findByUserId(id).flatMap(dbUser -> {
			return userService.updateUser(user);
		}).map(updatedUser -> ResponseEntity.ok(updatedUser)).defaultIfEmpty(ResponseEntity.badRequest().build());
	}

}