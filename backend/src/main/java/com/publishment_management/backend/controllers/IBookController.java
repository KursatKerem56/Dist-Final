package com.publishment_management.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.publishment_management.backend.dtos.DtoBook;
import com.publishment_management.backend.dtos.DtoBookIU;
import com.publishment_management.backend.models.RootEntity;

public interface IBookController {
	public ResponseEntity<RootEntity<List<DtoBook>>>  getBookList();
	public ResponseEntity<RootEntity<DtoBook>>  getBookById(Integer id);
	public ResponseEntity<RootEntity<DtoBook>>  saveBook(DtoBookIU book);
	public ResponseEntity<RootEntity<Void>> deleteBook(Integer id);
	public ResponseEntity<RootEntity<DtoBook>> changeBook(Integer id, DtoBookIU book);
}
