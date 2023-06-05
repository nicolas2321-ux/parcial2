package com.example.demo.dto;

import java.util.List;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;

import lombok.Data;

@Data
public class PlaylistWithSongsDto {

		private Playlist playlist;
		private List<Song> songs;
		private Integer duration;
}
