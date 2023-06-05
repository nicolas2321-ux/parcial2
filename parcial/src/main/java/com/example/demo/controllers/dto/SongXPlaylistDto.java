package com.example.demo.controllers.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class SongXPlaylistDto {
	
		private UUID songCode;
		private UUID playList;
		private Date date_added;
	
}

