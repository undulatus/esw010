package com.pointwest.workforce.planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
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
import com.pointwest.workforce.planner.service.VersionService;

@RestController
public class VersionController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@Autowired
	TemplateDataService templateDataService;

	@Autowired
	OpportunityService opportunityService;
	
	@Autowired
	VersionService versionService;
	
	@Autowired
	JsonParser parser;
	
	@Autowired
	Jackson2ObjectMapperBuilder objectMapper;
	
	//private static final Logger log = LoggerFactory.getLogger(VersionController.class);
	
	@RequestMapping(method=RequestMethod.POST, value="/opportunities/{opportunityId}/versions")
    public ResponseEntity<Object> saveVersion(@PathVariable int opportunityId, @RequestBody Version version) {
		Opportunity opportunity = opportunityService.fetchOpportunity(opportunityId);		
		if(opportunity == null) {
			return new ResponseEntity<>(new CustomError("Opportunity not found"), HttpStatus.NOT_FOUND);
		} else {
			//objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			//log.debug(opportunity.getJson().toString());
			return new ResponseEntity<>(opportunity, HttpStatus.OK);
		}
    }

}
