package com.publishment_management.backend.services;

import java.util.List;

import com.publishment_management.backend.dtos.DtoAuthor;
import com.publishment_management.backend.dtos.DtoAuthorIU;

public interface IAuthorService {
	public List<DtoAuthor> getAuthorList();
	public DtoAuthor getAuthorById(Integer id);
	public DtoAuthor saveAuthor(DtoAuthorIU author);
	public void deleteAuthor(Integer id);
	public DtoAuthor changeAuthor(Integer id, DtoAuthorIU author);
}
