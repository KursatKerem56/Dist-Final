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

import com.publishment_management.backend.controllers.IAuthorController;
import com.publishment_management.backend.controllers.RestBaseController;
import com.publishment_management.backend.dtos.DtoAuthor;
import com.publishment_management.backend.dtos.DtoAuthorIU;
import com.publishment_management.backend.models.RootEntity;
import com.publishment_management.backend.services.IAuthorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/author")
public class AuthorControllerImpl  extends RestBaseController implements IAuthorController {
	
	private final IAuthorService authorService;

	@Override
	@GetMapping(path = "/{id}")
	public ResponseEntity<RootEntity<DtoAuthor>>  getAuthorById(@PathVariable Integer id) {
		return ok(authorService.getAuthorById(id));
	}

	@Override
	@GetMapping(path = "/list")
	public ResponseEntity<RootEntity<List<DtoAuthor>>> getAuthorList() {
		return ok(authorService.getAuthorList());
	}

	@Override
	@PostMapping(path = "/save")
	public ResponseEntity<RootEntity<DtoAuthor>> saveAuthor(@Valid @RequestBody DtoAuthorIU author) {
		return ok(authorService.saveAuthor(author));
	}

	@Override
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<RootEntity<Void>> deleteAuthor(@PathVariable Integer id) {
		authorService.deleteAuthor(id);
		return ok(null);
	}

	@Override
	@PutMapping(path = "/change/{id}")
	public ResponseEntity<RootEntity<DtoAuthor>> changeAuthor(@PathVariable Integer id, @Valid @RequestBody DtoAuthorIU changedAuthor) {
		return ok(authorService.changeAuthor(id, changedAuthor));
	}
	
	
}
