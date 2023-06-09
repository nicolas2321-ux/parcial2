package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SongDto;
import com.example.demo.entities.Song;
import com.example.demo.service.SongService;

@RestController
@RequestMapping("/song")
public class SongController {

	@Autowired
	private SongService songservice;
	
	@GetMapping(name = "/" )
	public ResponseEntity<?> findallsongs(@RequestParam(required = false) String titleFragment, @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
	if(titleFragment != null) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Song>song = songservice.findByTitle(pageable,titleFragment);
		return new ResponseEntity<>(
				song, HttpStatus.OK);
	}else {
		List<Song> song= songservice.findAll();
		return new ResponseEntity<>(
				song, HttpStatus.OK);
	}
		
	}
	 @PostMapping("/create")
	public ResponseEntity<?> createSong(@RequestBody SongDto song) throws Exception{
		String title = song.getTitle();
		Integer duration = song.getDuration();
		Song songobj = new Song(title, duration);
		songservice.save(songobj);
		return ResponseEntity.ok("Cancion registrada correctamente");
	}
	 
}
