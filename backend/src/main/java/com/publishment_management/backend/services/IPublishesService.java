package com.publishment_management.backend.services;

import java.util.List;

import com.publishment_management.backend.dtos.DtoPublishes;
import com.publishment_management.backend.dtos.DtoPublishesIU;

public interface IPublishesService {
	public DtoPublishes getPublishesById(Integer id);
	
	public List<DtoPublishes> getPublishesList();
	
	public DtoPublishes savePublishes(DtoPublishesIU publishes);
	
	public void deletePublishes(Integer id);
	
	public DtoPublishes changePublishes(Integer id, DtoPublishesIU changedPublishes);

	public byte[] createPublishesPdfReport();
}
