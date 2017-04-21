package com.pointwest.workforce.planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.CustomError;
import com.pointwest.workforce.planner.domain.Opportunity;
import com.pointwest.workforce.planner.domain.Version;
import com.pointwest.workforce.planner.service.OpportunityService;
import com.pointwest.workforce.planner.service.ReferenceDataService;
import com.pointwest.workforce.planner.service.TemplateDataService;

@RestController
public class VersionController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@Autowired
	TemplateDataService templateDataService;

	@Autowired
	OpportunityService opportunityService;
	
	@Autowired
	JsonParser parser;
	
	@RequestMapping(method=RequestMethod.POST, value="/opportunities/{opportunityId}/versions")
    public ResponseEntity<Object> fetchOpportunity(@PathVariable int opportunityId, @RequestBody Version version) {
		Opportunity opportunity = opportunityService.fetchOpportunity(opportunityId);
		
		if(opportunity == null) {
			return new ResponseEntity<>(new CustomError("Opportunity not found"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(opportunity, HttpStatus.OK);
		}
    }

}
