package com.project.start.abysses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.start.abysses.dao.UserRepository;
import com.project.start.abysses.entity.User;

@Service
public class UserServiceImpl implements UserService {

private UserRepository userRepository;
	
	public UserServiceImpl (UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User findById(int theId) {
		
		Optional<User> result = userRepository.findById(theId);
		
		User theUser = null;
		if (result.isPresent()) {
			theUser = result.get();
		}
		else {
			// we didn't find the user
			throw new RuntimeException("Did not find user id - " + theId);
		}
		return theUser;
	}
	
	@Override
	public void save(User theUser) {
		userRepository.save(theUser);
	}
	
	@Override
	public void deleteById(int theId) {
		userRepository.deleteById(theId);
	}

	@Override
	public User findByEmail(String email) {
		
		List<User> result = userRepository.findByEmail(email);
		User theUser = null;
		
		if (result.size() > 0) {
			theUser = result.get(0);
        } else {
        	
        // we didn't find the user
        throw new RuntimeException("Did not find user email - " + email);
        
        }
		
		return theUser;
		
	}
	
}
