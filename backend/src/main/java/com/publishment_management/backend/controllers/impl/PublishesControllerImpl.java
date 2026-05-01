package com.publishment_management.backend.controllers.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publishment_management.backend.controllers.IPublishesController;
import com.publishment_management.backend.controllers.RestBaseController;
import com.publishment_management.backend.dtos.DtoPublishes;
import com.publishment_management.backend.dtos.DtoPublishesIU;
import com.publishment_management.backend.models.RootEntity;
import com.publishment_management.backend.services.IPublishesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/publishes")
@RequiredArgsConstructor
public class PublishesControllerImpl extends RestBaseController implements IPublishesController {

	private final IPublishesService publishesService;
	
	@Override
	@GetMapping(path = "/{id}")
	public ResponseEntity<RootEntity<DtoPublishes>> getPublishesById(Integer id) {
		return ok(publishesService.getPublishesById(id));
	}

	@Override
	@GetMapping(path = "/list")
	public ResponseEntity<RootEntity<List<DtoPublishes>>> getPublishesList() {
		// TODO Auto-generated method stub
		return ok(publishesService.getPublishesList());
	}

	@Override
	@PostMapping(path = "/list")
	public ResponseEntity<RootEntity<DtoPublishes>> savePublishes(DtoPublishesIU publishes) {
		return ok(publishesService.savePublishes(publishes));
	}

	@Override
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<RootEntity<Void>> deletePublishes(Integer id) {
		publishesService.deletePublishes(id);
		return ok(null);
	}

	@Override
	@PutMapping(path = "/change/{id}")
	public ResponseEntity<RootEntity<DtoPublishes>> changePublishes(Integer id, DtoPublishesIU changedPublishes) {
		// TODO Auto-generated method stub
		return ok(publishesService.changePublishes(id, changedPublishes));
	}
	
	

}
