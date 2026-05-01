package com.publishment_management.backend.controllers.impl;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<RootEntity<DtoPublishes>> getPublishesById(@PathVariable Integer id) {
		return ok(publishesService.getPublishesById(id));
	}

	@Override
	@GetMapping(path = "/list")
	public ResponseEntity<RootEntity<List<DtoPublishes>>> getPublishesList() {
		// TODO Auto-generated method stub
		return ok(publishesService.getPublishesList());
	}

	@Override
	@PostMapping(path = "/save")
	public ResponseEntity<RootEntity<DtoPublishes>> savePublishes(@Valid @RequestBody DtoPublishesIU publishes) {
		return ok(publishesService.savePublishes(publishes));
	}

	@Override
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<RootEntity<Void>> deletePublishes(@PathVariable Integer id) {
		publishesService.deletePublishes(id);
		return ok(null);
	}

	@Override
	@PutMapping(path = "/change/{id}")
	public ResponseEntity<RootEntity<DtoPublishes>> changePublishes(@PathVariable Integer id,@Valid @RequestBody DtoPublishesIU changedPublishes) {
		// TODO Auto-generated method stub
		return ok(publishesService.changePublishes(id, changedPublishes));
	}
	
	

}
