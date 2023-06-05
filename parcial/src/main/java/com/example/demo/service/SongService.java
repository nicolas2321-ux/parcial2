package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.entities.Song;

public interface SongService {
	
	void save(Song song) throws Exception;
	void deleteById(String id) throws Exception;
	Song findOneById(UUID id);
	List<Song> findAll();
	List<Song> findByTitle(String title);
}