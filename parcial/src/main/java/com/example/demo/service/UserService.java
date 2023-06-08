package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.dto.UserDto;
import com.example.demo.entities.Token;
import com.example.demo.entities.User;

public interface UserService {
	
	List<User> findall();
	 User getUserByUsername(String username);
	User register(User user);
	Token registerToken(User user) throws Exception;
	Boolean isTokenValid(User user, String token);
	void cleanTokens(User user) throws Exception;
	User login(String usernameOrEmail, String password);
	
	
	
}
