package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.entities.SongXPlaylist;

public interface SongXPlaylistRepository extends ListCrudRepository<SongXPlaylist, UUID> {

	List<SongXPlaylist> findSongsByPlaylist(Playlist playlist);
	
	 List<SongXPlaylist> findBySongAndPlaylist(Song song, Playlist playlist);

}
