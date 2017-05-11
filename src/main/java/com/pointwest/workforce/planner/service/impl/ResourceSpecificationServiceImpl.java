package com.pointwest.workforce.planner.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.data.ResourceSpecificationRepository;
import com.pointwest.workforce.planner.domain.ResourceSpecification;
import com.pointwest.workforce.planner.service.ResourceSpecificationService;

@Service
public class ResourceSpecificationServiceImpl implements ResourceSpecificationService {
	
	@Autowired
	public ResourceSpecificationRepository resourceSpecificationRepository;
	
	private static final Logger log = LoggerFactory.getLogger(ResourceSpecificationServiceImpl.class);

	@Override
	public List<ResourceSpecification> fetchAllResourceSpecifications() {
		List<ResourceSpecification> resourceSpecifications = new ArrayList<ResourceSpecification>(); 
		resourceSpecificationRepository.findAll().forEach(resourceSpecifications::add);
		return resourceSpecifications;
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
		if(resourceSpecification.getRole() == null) resourceSpecification.setRole(previousResourceSpecification.getRole());
		if(resourceSpecification.getPractice() == null) resourceSpecification.setPractice(previousResourceSpecification.getPractice());
		if(resourceSpecification.getPayLevel() == null) resourceSpecification.setPayLevel(previousResourceSpecification.getPayLevel());
		if(resourceSpecification.isBillable() == null) resourceSpecification.setBillable(previousResourceSpecification.isBillable());
		if(resourceSpecification.getOpportunityActivityId() == null) resourceSpecification.setOpportunityActivityId(previousResourceSpecification.getOpportunityActivityId());
		//sprint 2 changes
		if(resourceSpecification.getRoleStartDate() == null) resourceSpecification.setRoleStartDate(previousResourceSpecification.getRoleStartDate());
		if(resourceSpecification.getDurationInWeeks() == null) resourceSpecification.setDurationInWeeks(previousResourceSpecification.getDurationInWeeks());
		return resourceSpecificationRepository.save(resourceSpecification);
	}

	@Override
	public int deleteResourceSpecification(Long resourceSpecificationId) {
		int initialCount = resourceSpecificationRepository.countByResourceSpecificationId(resourceSpecificationId);
		resourceSpecificationRepository.delete(resourceSpecificationId);
		int postDeleteCount = resourceSpecificationRepository.countByResourceSpecificationId(resourceSpecificationId);
		return initialCount - postDeleteCount;
	}

	@Override
	public ResourceSpecification updateResourceSpecificationDates(Long resourceSpecificationId) {
		ResourceSpecification resourceSpecification = resourceSpecificationRepository.findOne(resourceSpecificationId);
		LocalDate opportunityStartLocalDate = resourceSpecificationRepository.findOpportunityStartDate(resourceSpecificationId);
		log.debug("opportunity start localdate " + opportunityStartLocalDate);
		Integer minWeek = resourceSpecificationRepository.findStartWeekOfResourceSpecification(resourceSpecificationId);
		Integer maxWeek = resourceSpecificationRepository.findEndWeekOfResourceSpecification(resourceSpecificationId);
		
		log.debug("opportunity start date " + opportunityStartLocalDate.toString());
		//Timestamp roleStartDate = Timestamp.valueOf(opportunityStartLocalDate.plusWeeks(minWeek - 1).atStartOfDay());
		//add offset months using standardized value
		int offsetMonth = (minWeek - 1) / 4;
		int offsetWeek = (minWeek - 1) % 4;
		Date roleStartDate = Date.valueOf(opportunityStartLocalDate.plusMonths(offsetMonth));
		//add offset weeks for standardized value
		roleStartDate = Date.valueOf(opportunityStartLocalDate.plusWeeks(offsetWeek));
		
		Double durationInWeeks = (maxWeek - minWeek) + 1.0;
		resourceSpecification.setRoleStartDate(roleStartDate);
		resourceSpecification.setDurationInWeeks(durationInWeeks);
		return resourceSpecificationRepository.save(resourceSpecification);
	}
	

	
}
