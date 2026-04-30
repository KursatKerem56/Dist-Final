package com.publishment_management.backend.services;

import java.util.List;

import com.publishment_management.backend.dtos.DtoBook;
import com.publishment_management.backend.dtos.DtoBookIU;

public interface IBookService {
	public List<DtoBook> getBookList();
	public DtoBook getBookById(Integer id);
	public DtoBook saveBook(DtoBookIU book);
	public void deleteBook(Integer id);
	public DtoBook changeBook(Integer id, DtoBookIU book);
}
