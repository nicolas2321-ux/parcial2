package com.example.demo.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entities.Token;
import com.example.demo.entities.User;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTtools;

import jakarta.transaction.Transactional;

@Service
public class UserImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JWTtools jwtTools;
	
	@Autowired
	private TokenRepository tokenRepository;



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



		@Override
		@Transactional(rollbackOn = Exception.class)
		public Token registerToken(User user) throws Exception {
			cleanTokens(user);
			
			String tokenString = jwtTools.generateToken(user);
			Token token = new Token(tokenString, user);
			
			tokenRepository.save(token);
			
			return token;
		}



		@Override
		public Boolean isTokenValid(User user, String token) {
			try {
				cleanTokens(user);
				List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
				
				tokens.stream()
					.filter(tk -> tk.getContent().equals(token))
					.findAny()
					.orElseThrow(() -> new Exception());
				
				return true;
			} catch (Exception e) {
				return false;
			}		
		}



		@Override
		@Transactional(rollbackOn = Exception.class)
		public void cleanTokens(User user) throws Exception {
			List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
			
			tokens.forEach(token -> {
				if(!jwtTools.verifyToken(token.getContent())) {
					token.setActive(false);
					tokenRepository.save(token);
				}
			});
			
		}
		@Override
		public User login(String usernameOrEmail, String password) {
			 User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
		        if (user != null && user.getPassword().equals(password)) {
		            return user;
		        }
		        return null; // El inicio de sesión falló
		}


		@Override
		public User findUserAuthenticated() {
			String username = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
			
			return userRepository.findByUsernameOrEmail(username, username);
		}
	







	




	

}
