package com.publishment_management.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.publishment_management.backend.dtos.DtoPublishes;
import com.publishment_management.backend.dtos.DtoPublishesIU;
import com.publishment_management.backend.models.Publishes;

@Mapper(componentModel = "spring")
public interface PublishesMapper {

	Publishes toEntity(DtoPublishesIU dtoPublishesIU);
	
	DtoPublishes toDto(Publishes poPublishes);
	
	List<DtoPublishes> toDtoList(List<Publishes> publishes);
	
	void copyPublishesFromDtoIU(DtoPublishesIU dto, @MappingTarget Publishes entity);
}
