package com.publishment_management.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.publishment_management.backend.dtos.DtoAuthor;
import com.publishment_management.backend.dtos.DtoAuthorIU;
import com.publishment_management.backend.models.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

	Author toEntity(DtoAuthorIU dtoAuthorIU);
	
	DtoAuthor toDto(Author author);
	
	List<DtoAuthor> toDtoList(List<Author> authors);
	
	void copyAuthorFromDtoIU(DtoAuthorIU dto, @MappingTarget Author entity);
}
