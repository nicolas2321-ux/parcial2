package com.example.demo.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.User;
import com.example.demo.repository.PlaylistRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PlaylistService;
@Service
public class PlaylistImpl implements PlaylistService  {
	@Autowired
	private PlaylistRepository playlistRepository;
	@Autowired
	private UserRepository userService;
	@Override
	public List<Playlist> findall() {
		return playlistRepository.findAll();
	}

	@Override
	public Playlist createPlaylist(Playlist playlist) {
		return playlistRepository.save(playlist);
	}

	@Override
	public Playlist findByCode(UUID code) {
		Playlist newPlaylist = playlistRepository.findByCode(code);
		return newPlaylist;
	}

	@Override
	public List<Playlist> getSongsInPlaylistsByUser(UUID username) {
		  User user = userService.findByCode(username);
		 
	        List<Playlist> playlists = playlistRepository.findByUserCode(username);
	       return playlists;
	}

	@Override
	public Page<Playlist> getSongsInPlaylistsByUserandTitle(Pageable pageable,UUID username, String title) {
		  User user = userService.findByCode(username);
		  Page<Playlist> playlist = playlistRepository.findByUserAndTitleContainingIgnoreCaseOrderByTitleAsc(pageable,user, title);
		return playlist;
	}

}
