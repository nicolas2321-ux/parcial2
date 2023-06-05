package com.example.demo.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controllers.dto.UserRegistrationDto;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.User;
import com.example.demo.service.PlaylistService;
import com.example.demo.service.SongService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PlaylistService playlistService;
	

	
	@Autowired
	private SongService songService;
	
	@GetMapping(name = "/")
	public ResponseEntity<?> findAllbook(){
		List<User> users = userService.findall();
		return new ResponseEntity<>(
				users, HttpStatus.OK);
	}

	  
	  @PostMapping("/signup")
	  public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto, BindingResult bindingResult) {
		  if(bindingResult.hasErrors()) {
			  return ResponseEntity.ok("Error al introducir las credenciales");
		  }else {
	      String username = registrationDto.getUsername();
	      String email = registrationDto.getEmail();
	      String password = registrationDto.getPassword();
	      
	  	  User usernameVer = userService.getUserByUsername(username);
	  	  User emailver = userService.getUserByUsername(email);
	  	  if(usernameVer != null || emailver != null) {
	  		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Credenciales ya tomadas");	  	
	  	  }else {

	      User newUser = new User(username, email, password);
	      userService.register(newUser);
	      
	      return ResponseEntity.ok("Usuario registrado exitosamente");
	  }
		  }
	  }
	  
	  
	  
		@PostMapping("/playlist")
		 public ResponseEntity<?> getPlaylist(@RequestParam(required = false) String username, @RequestParam(required = false) String title){
			if(username == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("identificador es requerido");
			}else {
			if(title != null) {
				User user = userService.getUserByUsername(username);
				if(user != null) {
					 List<Playlist> playlists = playlistService.getSongsInPlaylistsByUserandTitle(user.getCode(), title);
					  return new ResponseEntity<>(
								playlists, HttpStatus.OK);
				}else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún usuario con el identificador proporcionado");
				}
				
				
				
			}
			else {
			User user = userService.getUserByUsername(username);
			if(user != null) {
				 List<Playlist> playlists = playlistService.getSongsInPlaylistsByUser(user.getCode());
				  return new ResponseEntity<>(
							playlists, HttpStatus.OK);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún usuario con el identificador proporcionado");
			}
			}
			
			
		  }
		}

}

