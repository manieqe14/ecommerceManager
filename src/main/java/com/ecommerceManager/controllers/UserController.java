package com.ecommerceManager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerceManager.data.RoleRepo;
import com.ecommerceManager.data.User;
import com.ecommerceManager.data.UserRepo;

@RestController
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@PostMapping("/createUser")
	public String createUser(@RequestBody User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(roleRepo.findByName("USER"));
		userRepo.save(user);
		return "OK";
		
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		List<User> users = userRepo.findAll();
		return users;
	}
	
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No user");
	}

}
