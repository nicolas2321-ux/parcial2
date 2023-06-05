package com.example.demo.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "songxplaylist")
public class SongXPlaylist {

	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "song_code", nullable = false)
	private Song song;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "playlist_code", nullable = false)
	private Playlist playlist;
	
	@Column(name ="date_added")
	private Date date_added;
	
	
	
	public SongXPlaylist(Song song, Playlist playlist, Date date_added) {
		super();
		this.song = song;
		this.playlist = playlist;
		this.date_added = date_added;
	}


	
	
}