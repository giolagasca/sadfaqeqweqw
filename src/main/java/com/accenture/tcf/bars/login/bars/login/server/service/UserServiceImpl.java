package com.accenture.tcf.bars.login.bars.login.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.tcf.bars.login.bars.login.server.domain.User;
import com.accenture.tcf.bars.login.bars.login.server.domain.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		User theUser = new User();
		theUser.setUsername(user.getUsername());
		theUser.setPassword(user.getPassword());
		theUser.setRole(user.getRole());
		return userRepository.save(theUser);		
	}
	
	@Override
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		User theUser = userRepository.getOne(user.getId());
		theUser.setUsername(user.getUsername());
		theUser.setPassword(user.getPassword());
		theUser.setRole(user.getRole());
		return userRepository.save(theUser);		
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	

}
