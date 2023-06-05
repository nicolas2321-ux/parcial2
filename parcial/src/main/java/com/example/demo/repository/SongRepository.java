package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.entities.Song;

public interface SongRepository extends ListCrudRepository<Song, UUID> {
	Song findByCode(UUID id);
	List<Song> findByTitleContainingIgnoreCase(String partialTitle);
	
}
