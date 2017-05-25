package com.pointwest.workforce.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.ServiceType;
import com.pointwest.workforce.planner.service.ReferenceDataService;

@RestController
public class ServiceTypeController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@RequestMapping("/servicetypes")
    public List<ServiceType> fetchAllServiceType() {
       return referenceDataService.fetchAllServiceType();
    }
	
	@RequestMapping("/servicetypes/{serviceTypeId}")
    public ServiceType fetchServiceType(@PathVariable int serviceTypeId) {
       return referenceDataService.fetchServiceType(serviceTypeId);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/servicetypes")
    public void addServiceType(@RequestBody ServiceType serviceType) {
		referenceDataService.addServiceType(serviceType);
    }

}
