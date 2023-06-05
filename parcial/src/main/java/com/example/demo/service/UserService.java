package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.dto.UserDto;
import com.example.demo.entities.User;

public interface UserService {
	
	List<User> findall();
	 User getUserByUsername(String username);
	User register(User user);
	
	
	
}
