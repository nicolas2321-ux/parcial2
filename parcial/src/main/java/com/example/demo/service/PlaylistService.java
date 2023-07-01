package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entities.Playlist;

public interface PlaylistService {
	List<Playlist> findall();
	Playlist createPlaylist(Playlist playlist);
	Playlist findByCode(UUID code);
	List<Playlist> getSongsInPlaylistsByUser(UUID username);
	Page<Playlist> getSongsInPlaylistsByUserandTitle(Pageable pageable,UUID username, String title);
	
}