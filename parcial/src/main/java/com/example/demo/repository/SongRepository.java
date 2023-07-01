package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.entities.Song;

public interface SongRepository extends ListCrudRepository<Song, UUID> {
	Song findByCode(UUID id);
	Page<Song> findByTitleContainingIgnoreCase(Pageable pageable, String partialTitle);
	
}
