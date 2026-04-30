package com.publishment_management.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.publishment_management.backend.dtos.DtoPublishes;
import com.publishment_management.backend.dtos.DtoPublishesIU;
import com.publishment_management.backend.models.RootEntity;

public interface IPublishesController {
	public ResponseEntity<RootEntity<DtoPublishes>> getPublishesById(Integer id);
	
	public ResponseEntity<RootEntity<List<DtoPublishes>>> getPublishesList();
	
	public ResponseEntity<RootEntity<DtoPublishes>> savePublishes(DtoPublishesIU publishes);
	
	public ResponseEntity<RootEntity<Void>> deletePublishes(Integer id);
	
	public ResponseEntity<RootEntity<DtoPublishes>> changePublishes(Integer id, DtoPublishesIU changedPublishes);
}
