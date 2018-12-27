package com.accenture.tcf.bars.login.bars.login.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.accenture.tcf.bars.login.bars.login.server.domain.User;

@Component
public interface UserService {
	
	public User createUser(User user);
	public User findByUsername(String username);
	public Optional<User> findById(int id);
	public List<User> findAllUsers();
	public User updateUser(User user);
	public void deleteUser(User user);

}
