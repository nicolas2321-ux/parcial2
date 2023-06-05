package com.example.demo.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserImpl implements UserService {

	@Autowired
	private UserRepository userRepository;




	@Override
	public List<User> findall() {
		
		return userRepository.findAll();
	}



	@Override
	public User register(User user) {
		 return userRepository.save(user);
	}



	  @Override
	    public User getUserByUsername(String username) {
	        return userRepository.findByUsernameOrEmail(username, username);
	    }







	




	

}
