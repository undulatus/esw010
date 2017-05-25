package com.pointwest.workforce.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.CustomError;
import com.pointwest.workforce.planner.domain.ResourceSpecification;
import com.pointwest.workforce.planner.service.ResourceSpecificationService;

@RestController
public class ResourceSpecificationController {
	
	@Autowired
	ResourceSpecificationService resourceSpecificationService;
	
	@RequestMapping(method=RequestMethod.GET, value="/resourcespecifications")
    public ResponseEntity<Object> fetchAllOpportunities() {
		List<ResourceSpecification> resourceSpecifications =resourceSpecificationService.fetchAllResourceSpecifications();
		if(resourceSpecifications == null || resourceSpecifications.isEmpty()) {
			return new ResponseEntity<>(new CustomError("No Resource Specifications retrieved"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(resourceSpecifications, HttpStatus.OK);
		}
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/resourcespecifications/{resourceSpecificationId}")
    public ResponseEntity<Object> fetchResourceSpecification(@PathVariable Long resourceSpecificationId) {
		ResourceSpecification resourceSpecification = resourceSpecificationService.fetchResourceSpecification(resourceSpecificationId);
		if(resourceSpecification == null) {
			return new ResponseEntity<>(new CustomError("Resource Specification not found"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(resourceSpecification, HttpStatus.OK);
		}
    }
	

	@RequestMapping(method=RequestMethod.POST, value="/resourcespecifications")
    public ResponseEntity<Object> saveResourceSpecification(@RequestBody(required=false) ResourceSpecification resourceSpecification) {
		ResourceSpecification savedResourceSpecification = null;
		boolean isNew = false;
		if(resourceSpecification==null) {
			savedResourceSpecification = resourceSpecificationService.saveResourceSpecification(new ResourceSpecification());
			isNew = true;
		} else if(resourceSpecification.getResourceSpecificationId() == null) {
			savedResourceSpecification = resourceSpecificationService.saveResourceSpecification(resourceSpecification);
			isNew = true;
		} else {
			savedResourceSpecification = resourceSpecificationService.updateResourceSpecification(resourceSpecification, resourceSpecification.getResourceSpecificationId());
			isNew = false;
		}
		if(savedResourceSpecification==null) {
			return new ResponseEntity<>(new CustomError("Invalid inputs, not saved"), HttpStatus.BAD_REQUEST);
		} else {
			if(isNew) {
				return new ResponseEntity<>(savedResourceSpecification, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(savedResourceSpecification, HttpStatus.OK);
			}
		}
    }
	
	@RequestMapping(method=RequestMethod.PUT, value="/resourcespecifications/{resourceSpecificationId}")
    public ResponseEntity<Object> updateResourceSpecification(@PathVariable Long resourceSpecificationId, @RequestBody ResourceSpecification resourceSpecification) {
		ResourceSpecification savedResourceSpecification = null;
		Long idInRequestBody = resourceSpecification.getResourceSpecificationId();
		if ( (idInRequestBody != null) && ((idInRequestBody.compareTo(resourceSpecificationId)) != 0) ) {
			//unmatched object and url id's
			return new ResponseEntity<>(new CustomError("Unmatched ID's in RequestBody and URL"), HttpStatus.BAD_REQUEST);
		}
		savedResourceSpecification = resourceSpecificationService.updateResourceSpecification(resourceSpecification, resourceSpecificationId);
		if(savedResourceSpecification==null) {
			return new ResponseEntity<>(resourceSpecification, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(savedResourceSpecification, HttpStatus.OK);
		}
    }
	
	@RequestMapping(method=RequestMethod.DELETE, value="/resourcespecifications/{resourceSpecificationId}")
    public ResponseEntity<Object> deleteResourceSpecification(@PathVariable Long resourceSpecificationId) {
		int deleteCount = resourceSpecificationService.deleteResourceSpecification(resourceSpecificationId);
		if(deleteCount > 0) {
			return new ResponseEntity<>(deleteCount, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(deleteCount, HttpStatus.BAD_REQUEST);
		}
    }
	
	
}
