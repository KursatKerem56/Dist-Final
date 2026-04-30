package com.publishment_management.backend.dtos;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoBookIU {
	
	@NotEmpty(message = "Title field cannot be left empty")
	private String title;
	
	private String publisher;
	
}
