package com.publishment_management.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.publishment_management.backend.dtos.DtoAuthor;
import com.publishment_management.backend.dtos.DtoAuthorIU;
import com.publishment_management.backend.models.Author;

@Mapper(componentModel = "spring", uses = {PublishesMapper.class})
public interface AuthorMapper {

	@Mapping(target = "id", ignore = true)
	Author toEntity(DtoAuthorIU dtoAuthorIU);

	DtoAuthor toDto(Author author);

	List<DtoAuthor> toDtoList(List<Author> authors);

	@Mapping(target = "id", ignore = true)
	void copyAuthorFromDtoIU(DtoAuthorIU dto, @MappingTarget Author entity);
}