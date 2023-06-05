package com.example.demo.controllers.dto;

import java.util.Date;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SongDto {
	
	@NotEmpty
	private String code;
	
	@NotEmpty
	private Date fecha;

	public UUID toUUID(String codigo) {
		UUID code = UUID.fromString(codigo);
		return code;
		
	}
	
	
}
