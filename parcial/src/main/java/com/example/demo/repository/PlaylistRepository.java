package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.User;

public interface PlaylistRepository extends ListCrudRepository<Playlist, UUID> {
	  Playlist findByCode(UUID id);	
	  List<Playlist> findByUserCode(UUID userCode);
	  Playlist findByUserAndCode(User user, UUID code);
	  List<Playlist> findByUserOrderByTitleAsc(User user);
	  List<Playlist> findByUserAndTitleContainingIgnoreCaseOrderByTitleAsc(User user, String title);
}
