package com.project.start.abysses.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.start.abysses.entity.User;
import com.project.start.abysses.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	
			private UserService userService;
			
			//@Autowired
		    //private PasswordEncoder passwordEncoder;

			
			@Autowired
			public UserRestController(UserService theUserService) {
				userService = theUserService;
			}
			
			@GetMapping("/users")
			public List<User> getUsers(){
				return userService.findAll();
			}
			
			@GetMapping("/users/{userId}")
		    public User getUserById(@PathVariable int userId) {
		        User theUser = userService.findById(userId);
		        if( theUser == null) {
		        	throw new RuntimeException("User id not found - " + userId);
		        }
		        return theUser;
		    }

			@GetMapping("/register")
			public String getAlbumsHello() {
				return "Register page which no need any authentication";
			}
			
			
			@RequestMapping("/user")
			public User getUserDetailsAfterLogin(Authentication authentication){
				 
				 User theUser = userService.findByEmail(authentication.getName());
				 
				 return theUser;
			}
			
			
			

}
