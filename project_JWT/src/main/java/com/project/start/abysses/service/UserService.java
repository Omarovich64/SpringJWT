package com.project.start.abysses.service;

import java.util.List;

import com.project.start.abysses.entity.User;


public interface UserService {
	
	public List<User> findAll();
	
	public User findById(int theId);
	
	public void save(User theUser);
	
	public void deleteById(int theId);
	
	public User findByEmail(String email);

}
