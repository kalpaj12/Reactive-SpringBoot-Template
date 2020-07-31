package com.example.demo.service.implementation;

import java.util.List;
import java.util.Optional;

import com.example.demo.persistence.model.User;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findByUserId(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

}