package com.publishment_management.backend.controllers.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publishment_management.backend.controllers.IBookController;
import com.publishment_management.backend.controllers.RestBaseController;
import com.publishment_management.backend.dtos.DtoBook;
import com.publishment_management.backend.dtos.DtoBookIU;
import com.publishment_management.backend.models.RootEntity;
import com.publishment_management.backend.services.IBookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/book")
public class BookControllerImpl extends RestBaseController implements IBookController{

	private final IBookService bookService;
	
	@Override
	@GetMapping(path = "/list")
	public ResponseEntity<RootEntity<List<DtoBook>>> getBookList() {
		return ok(bookService.getBookList());
	}

	@Override
	@GetMapping(path = "/{id}")
	public ResponseEntity<RootEntity<DtoBook>> getBookById(@PathVariable Integer id) {
		return ok(bookService.getBookById(id));
	}

	@Override
	@PostMapping(path = "/list")
	public ResponseEntity<RootEntity<DtoBook>> saveBook(@Valid @RequestBody DtoBookIU book) {
		return ok(bookService.saveBook(book));
	}

	@Override
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<RootEntity<Void>> deleteBook(@PathVariable Integer id) {
		bookService.deleteBook(id);
		return ok(null);
	}
	

	@Override
	@PutMapping(path = "/change{id}")
	public ResponseEntity<RootEntity<DtoBook>> changeBook(@PathVariable Integer id,@Valid @RequestBody DtoBookIU book) {
		return ok(bookService.changeBook(id, book));
	}
	
	

}
