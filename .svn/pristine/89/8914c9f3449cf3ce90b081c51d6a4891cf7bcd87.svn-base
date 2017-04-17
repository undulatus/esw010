package com.pointwest.workforce.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.ServiceLine;
import com.pointwest.workforce.planner.service.ReferenceDataService;

@RestController
public class ServiceLineController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@RequestMapping("/servicelines")
    public List<ServiceLine> fetchAllServicLine() {
       return referenceDataService.fetchAllServiceLine();
    }
	
	@RequestMapping("/servicelines/{serviceLineId}")
    public ServiceLine fetchServiceLine(@PathVariable int serviceLineId) {
       return referenceDataService.fetchServiceLine(serviceLineId);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/servicelines")
    public void addServiceLine(@RequestBody ServiceLine serviceLine) {
		referenceDataService.addServiceLine(serviceLine);
    }

}
