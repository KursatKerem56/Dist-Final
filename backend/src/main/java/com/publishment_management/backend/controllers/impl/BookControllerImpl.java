package com.publishment_management.backend.controllers.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publishment_management.backend.controllers.IBookController;
import com.publishment_management.backend.dtos.DtoBook;
import com.publishment_management.backend.dtos.DtoBookIU;
import com.publishment_management.backend.models.RootEntity;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookControllerImpl implements IBookController{

	@Override
	public ResponseEntity<RootEntity<List<DtoBook>>> getBookList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<RootEntity<DtoBook>> getBookById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<RootEntity<DtoBook>> saveBook(DtoBookIU book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<RootEntity<Void>> deleteBook(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<RootEntity<DtoBook>> changeBook(Integer id, DtoBookIU book) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
