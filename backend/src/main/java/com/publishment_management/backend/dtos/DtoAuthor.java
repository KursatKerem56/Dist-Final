package com.publishment_management.backend.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoAuthor {
	
	private Integer id;
	
	private String name;
	
	private String address;

	private String image;
	
	@JsonIgnoreProperties({"authorId" , "authorName"})
	private List<DtoPublishes> publishes;

	
}
