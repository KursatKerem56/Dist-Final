package com.publishment_management.backend.controllers.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publishment_management.backend.controllers.IPublishesController;
import com.publishment_management.backend.dtos.DtoPublishes;
import com.publishment_management.backend.dtos.DtoPublishesIU;
import com.publishment_management.backend.models.RootEntity;

@RestController
@RequestMapping(path = "api/v1/publishes")
public class PublishesControllerImpl implements IPublishesController {

	@Override
	public ResponseEntity<RootEntity<DtoPublishes>> getPublishesById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<RootEntity<List<DtoPublishes>>> getPublishesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<RootEntity<DtoPublishes>> savePublishes(DtoPublishesIU publishes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<RootEntity<Void>> deletePublishes(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<RootEntity<DtoPublishes>> changePublishes(Integer id, DtoPublishesIU changedPublishes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
