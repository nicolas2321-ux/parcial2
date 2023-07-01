package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entities.Song;

public interface SongService {
	
	void save(Song song) throws Exception;
	void deleteById(String id) throws Exception;
	Song findOneById(UUID id);
	List<Song> findAll();
	Page<Song> findByTitle(Pageable pageable, String title);
}