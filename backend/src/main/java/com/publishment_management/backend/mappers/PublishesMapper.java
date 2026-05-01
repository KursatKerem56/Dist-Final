package com.publishment_management.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.publishment_management.backend.dtos.DtoPublishes;
import com.publishment_management.backend.dtos.DtoPublishesIU;
import com.publishment_management.backend.models.Publishes;

@Mapper(componentModel = "spring")
public interface PublishesMapper {

	@Mapping(source = "bookId", target = "book.id")
	@Mapping(source = "authorId", target = "author.id")
	Publishes toEntity(DtoPublishesIU dtoPublishesIU);

	@Mapping(source = "author.id", target = "authorId")
	@Mapping(source = "author.name", target = "authorName") // Author entity'sindeki alan adının 'name' olduğunu varsayıyorum
	@Mapping(source = "book.id", target = "bookId")
	@Mapping(source = "book.title", target = "bookTitle")
	DtoPublishes toDto(Publishes poPublishes);

	List<DtoPublishes> toDtoList(List<Publishes> publishes);

	@Mapping(source = "bookId", target = "book.id")
	@Mapping(source = "authorId", target = "author.id")
	void copyPublishesFromDtoIU(DtoPublishesIU dto, @MappingTarget Publishes entity);
}
