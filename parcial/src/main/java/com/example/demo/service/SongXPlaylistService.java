package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.dto.PlaylistWithSongsDto;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.entities.SongXPlaylist;

public interface SongXPlaylistService {
	List<SongXPlaylist> getAll();
	void insertSongsIntoPlaylist(SongXPlaylist newSong);
	 PlaylistWithSongsDto findSongsByPlaylist(Playlist playlist);
	  boolean isSongInPlaylist(Song songCode, Playlist playlistCode);
}
