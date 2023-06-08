package com.example.demo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controllers.dto.PlaylistDto;
import com.example.demo.dto.PlaylistUserDto;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.entities.User;
import com.example.demo.service.PlaylistService;
import com.example.demo.service.SongService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SongService songService;
	
	@GetMapping(name = "/finplaylist")
	public ResponseEntity<?> findAllbook(){
		List<Playlist> playlist= playlistService.findall();
		return new ResponseEntity<>(
				playlist, HttpStatus.OK);
	}
	
	
	  @PostMapping("/")
	public ResponseEntity<?> createPlaylist(@Valid @RequestBody PlaylistDto playlist, BindingResult bindingresult){
		 if(bindingresult.hasErrors()) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al llenar los campos");
		 }else {
		String title = playlist.getTitle();
		String description = playlist.getDescription();
		
		 User user = userService.findUserAuthenticated();
		if(user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún usuario con el identificador proporcionado");
		}else {
			Playlist playlistObj = new Playlist(title, description, user);
			playlistService.createPlaylist(playlistObj);
			  return ResponseEntity.ok("Playlist creada correctamente");
		}
	
		 }
	}

	

	  

}
