package com.publishment_management.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.publishment_management.backend.dtos.DtoAuthor;
import com.publishment_management.backend.dtos.DtoAuthorIU;
import com.publishment_management.backend.models.RootEntity;

public interface IAuthorController {
	public ResponseEntity<RootEntity<DtoAuthor>>  getAuthorById(Integer id);
	
	public ResponseEntity<RootEntity<List<DtoAuthor>>>  getAuthorList();
	
	public ResponseEntity<RootEntity<DtoAuthor>> saveAuthor(DtoAuthorIU author);
	
	public ResponseEntity<RootEntity<Void>> deleteAuthor(Integer id);
	
	public ResponseEntity<RootEntity<DtoAuthor>> changeAuthor(Integer id, DtoAuthorIU changedAuthor);
}