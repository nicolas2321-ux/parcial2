package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.entities.Playlist;

public interface PlaylistService {
	List<Playlist> findall();
	Playlist createPlaylist(Playlist playlist);
	Playlist findByCode(UUID code);
	List<Playlist> getSongsInPlaylistsByUser(UUID username);
	List<Playlist> getSongsInPlaylistsByUserandTitle(UUID username, String title);
	
}