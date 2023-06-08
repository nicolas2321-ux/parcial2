package com.example.demo.controllers.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PlaylistDto {
	@NotEmpty
	private String title;
	@NotEmpty
	private String description;

}