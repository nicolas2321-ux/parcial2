package com.example.demo.controllers.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegistrationDto {
	@NotEmpty
	private String Username;
	
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Correo electrónico inválido")
	@NotEmpty
	private String Email;
	
	@NotEmpty
	 @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
     message = "La contraseña debe tener al menos 8 caracteres, 1 mayúscula, 1 minúscula, 1 dígito y 1 carácter especial")
	private String Password;
}

