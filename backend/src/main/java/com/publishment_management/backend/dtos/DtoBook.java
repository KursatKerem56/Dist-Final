package com.publishment_management.backend.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoBook {

	private Integer id;
	
	private String title;
	
	private String publisher;
	
	@JsonIgnoreProperties({"bookId" , "bookTitle"})
	List<DtoPublishes> publishes;
	
}
