package com.example.demo.controllers.dto;

import com.example.demo.entities.Token;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class tokensDTO {

	private String token;
	
	public tokensDTO(Token token) {
		this.token = token.getContent();
	}
	
}
