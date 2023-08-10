package com.project.start.abysses.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.start.abysses.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByEmail(String email);

}