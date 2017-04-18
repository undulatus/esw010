package com.pointwest.workforce.planner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.data.ResourceSpecificationRepository;
import com.pointwest.workforce.planner.domain.ResourceSpecification;
import com.pointwest.workforce.planner.service.ResourceSpecificationService;

@Service
public class ResourceSpecificationServiceImpl implements ResourceSpecificationService {
	
	@Autowired
	public ResourceSpecificationRepository resourceSpecificationRepository;

	@Override
	public List<ResourceSpecification> fetchAllResourceSpecifications() {
		List<ResourceSpecification> opportunityActivies = new ArrayList<ResourceSpecification>(); 
		resourceSpecificationRepository.findAll().forEach(opportunityActivies::add);
		return opportunityActivies;
	}

	@Override
	public ResourceSpecification fetchResourceSpecification(Long resourceSpecificationId) {
		return resourceSpecificationRepository.findOne(resourceSpecificationId);
		
	}

	@Override
	public ResourceSpecification saveResourceSpecification(ResourceSpecification resourceSpecification) {
		return resourceSpecificationRepository.save(resourceSpecification);
	}
	
	@Override
	public ResourceSpecification updateResourceSpecification(ResourceSpecification resourceSpecification,
			Long resourceSpecificationId) {
		//if id not supplied in request body then set it
		if(resourceSpecification.getResourceSpecificationId() == null) resourceSpecification.setResourceSpecificationId(resourceSpecificationId);
		//do not save null values but set the previous values into it
		ResourceSpecification previousResourceSpecification = resourceSpecificationRepository.findOne(resourceSpecificationId);
		return resourceSpecificationRepository.save(resourceSpecification);
	}

	@Override
	public int deleteResourceSpecification(Long resourceSpecificationId) {
		int initialCount = resourceSpecificationRepository.countByResourceSpecificationId(resourceSpecificationId);
		resourceSpecificationRepository.delete(resourceSpecificationId);
		int postDeleteCount = resourceSpecificationRepository.countByResourceSpecificationId(resourceSpecificationId);
		return initialCount - postDeleteCount;
	}
	

	
}
