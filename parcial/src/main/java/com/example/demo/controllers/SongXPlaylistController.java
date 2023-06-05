package com.example.demo.controllers;

import java.util.Date;
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

import com.example.demo.controllers.dto.SongDto;
import com.example.demo.controllers.dto.SongXPlaylistDto;
import com.example.demo.dto.PlaylistWithSongsDto;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.entities.SongXPlaylist;
import com.example.demo.service.PlaylistService;
import com.example.demo.service.SongService;
import com.example.demo.service.SongXPlaylistService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class SongXPlaylistController {

	@Autowired
	private SongXPlaylistService songXplayService;
	
	@Autowired 
	private SongService songservice;
	@Autowired 
	private PlaylistService playlistService;
	
	@GetMapping("/songsplay/{playlist}")
	public ResponseEntity<?> findallsongs(@PathVariable("playlist") UUID playlist){
		if(playlist == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró codigo de playlist");
		}{
		Playlist playlistSongs = playlistService.findByCode(playlist);
		if(playlistSongs == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningúna playlist con el identificador proporcionado");
		}else {
		PlaylistWithSongsDto songs = songXplayService.findSongsByPlaylist(playlistSongs);
		return new ResponseEntity<>(
				songs, HttpStatus.OK);
		}
		}
	}
	
	@PostMapping("/newSongPlaylist")
	public ResponseEntity<?> createNewSongs(@Valid @RequestParam(required = false) String playlistCode, @RequestBody SongDto songDto, BindingResult bindingresult){
		if(bindingresult.hasErrors()) {
			System.out.print("entre");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Se detectaron campos vacios");
		}else {
			//System.out.print("entre");
		UUID songCode =songDto.toUUID(songDto.getCode());
		UUID playlistCodigo = songDto.toUUID(playlistCode);
		Date date_added = songDto.getFecha();
		
		
		Song getSong = songservice.findOneById(songCode);
		Playlist getPlaylist = playlistService.findByCode(playlistCodigo);
		if(getSong == null || getPlaylist == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la playlist o la cancion");
		}else {
		
		boolean songs = songXplayService.isSongInPlaylist(getSong,getPlaylist);
		System.out.print(songs);
		if(songs) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cancion ya esta en la playlist");
		}else {
		SongXPlaylist songplay = new SongXPlaylist(getSong, getPlaylist, date_added);
		songXplayService.insertSongsIntoPlaylist(songplay);
		return ResponseEntity.ok("Cancion ingresada correctamente");
		}
		}
	}
	}
}
