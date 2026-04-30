package com.publishment_management.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoAuthorIU {

	@NotBlank(message = "Name field cannot be left empty")
	private String name;
	
	private String address;
}
