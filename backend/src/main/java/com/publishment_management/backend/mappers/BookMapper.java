package com.publishment_management.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.publishment_management.backend.dtos.DtoBook;
import com.publishment_management.backend.dtos.DtoBookIU;
import com.publishment_management.backend.models.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

	@Mapping(target = "id", ignore = true)
	Book toEntity(DtoBookIU dtoBookIU);
	
	DtoBook toDto(Book book);
	
	List<DtoBook> toDtoList(List<Book> books);
	
	void copyBookFromDtoIU(DtoBookIU dto, @MappingTarget Book entity);
}
