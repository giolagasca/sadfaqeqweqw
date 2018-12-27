package com.accenture.tcf.bars.login.bars.login.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.tcf.bars.login.bars.login.server.domain.User;
import com.accenture.tcf.bars.login.bars.login.server.exception.UserNotFoundException;
import com.accenture.tcf.bars.login.bars.login.server.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) throws UserNotFoundException {
		User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for id: " + id));
		log.info("{}", user);
		return ResponseEntity.ok().body(user);
		
	}
	
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userService.createUser(user);		
	}
	
	@PostMapping("/users/{username}/{password}")
	public User login(@PathVariable String username, @PathVariable String password) {
		User user = userService.findByUsername(username);
		if(user==null)
			throw new UserNotFoundException("User not found");
		if(user.getPassword().equals(password))
			return user;
		throw new UserNotFoundException("Invalid password");
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody User userDetails) 
			throws UserNotFoundException {
		User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for id: " + id));
		log.info("{}", user);
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		user.setRole(userDetails.getRole());
		final User updatedUser = userService.updateUser(user);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable int id) throws UserNotFoundException {
		User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for id: " + id));
		log.info("{}", user);
		userService.deleteUser(user);
		Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
	}
	
	
	
}
