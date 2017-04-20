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

import com.pointwest.workforce.planner.domain.Activity;
import com.pointwest.workforce.planner.domain.CustomError;
import com.pointwest.workforce.planner.domain.Opportunity;
import com.pointwest.workforce.planner.domain.OpportunityActivity;
import com.pointwest.workforce.planner.service.OpportunityActivityService;
import com.pointwest.workforce.planner.service.OpportunityService;
import com.pointwest.workforce.planner.service.TemplateDataService;

@RestController
public class OpportunityController {
	
	@Autowired
	OpportunityService opportunityService;
	
	@Autowired
	OpportunityActivityService opportunityActivityService;
	
	@Autowired
	TemplateDataService templateDataService;
	
	@RequestMapping(method=RequestMethod.GET, value="/opportunities")
    public ResponseEntity<Object> fetchAllOpportunities() {
		List<Opportunity> opportunities = opportunityService.fetchAllOpportunities();
		if(opportunities == null || opportunities.isEmpty()) {
			return new ResponseEntity<>(new CustomError("No opportunities retrieved"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(opportunities, HttpStatus.OK);
		}
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/opportunities/{opportunityId}")
    public ResponseEntity<Object> fetchOpportunity(@PathVariable int opportunityId) {
		Opportunity opportunity = opportunityService.fetchOpportunity(opportunityId);
		if(opportunity == null) {
			return new ResponseEntity<>(new CustomError("Opportunity not found"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(opportunity, HttpStatus.OK);
		}
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/users/{username}/opportunities")
    public ResponseEntity<Object> fetchOpportunity(@PathVariable String username) {
       List<Opportunity> opportunities = opportunityService.fetchOpportunitiesByUsername(username);
		if(opportunities == null || opportunities.isEmpty()) {
			return new ResponseEntity<>(new CustomError("No opportunities retrieved"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(opportunities, HttpStatus.OK);
		}
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/users/{username}/opportunities/other")
    public ResponseEntity<Object> fetchNotOwnedOpportunity(@PathVariable String username) {
		List<Opportunity> opportunities = opportunityService.fetchNotOwnedOpportunitiesByUsername(username);
		if(opportunities == null || opportunities.isEmpty()) {
			return new ResponseEntity<>(new CustomError("No opportunities retrieved"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(opportunities, HttpStatus.OK);
		}
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/opportunities")
    public ResponseEntity<Object> saveOpportunity(@RequestBody(required=false) Opportunity opportunity) {
		Opportunity savedOpportunity = null;
		boolean isNew = false;
		if(opportunity==null) {
			savedOpportunity = opportunityService.saveOpportunity(new Opportunity());
			isNew = true;
		} else if(opportunity.getOpportunityId() == null) {
			savedOpportunity = opportunityService.saveOpportunity(opportunity);
			isNew = true;
		} else {
			savedOpportunity = opportunityService.updateOpportunity(opportunity, opportunity.getOpportunityId());
			isNew = false;
		}
		if(savedOpportunity==null) {
			return new ResponseEntity<>(new CustomError("Incorrect inputs, not saved"), HttpStatus.BAD_REQUEST);
		} else {
			if(isNew) {
				return new ResponseEntity<>(savedOpportunity, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(savedOpportunity, HttpStatus.OK);
			}
		}
    }
	
	@RequestMapping(method=RequestMethod.PUT, value="/opportunities/{opportunityId}")
    public ResponseEntity<Object> updateOpportunity(@PathVariable Long opportunityId, @RequestBody(required=true) Opportunity opportunity) {
		Opportunity savedOpportunity = null;
		Long idInRequestBody = opportunity.getOpportunityId();
		if ( (idInRequestBody != null) && ((idInRequestBody.compareTo(opportunityId)) != 0) ) {
			//unmatched object and url id's
			return new ResponseEntity<>(new CustomError("Unmatched ID's in RequestBody and URL"), HttpStatus.BAD_REQUEST);
		}
		savedOpportunity = opportunityService.updateOpportunity(opportunity, opportunityId);
		if(savedOpportunity==null) {
			return new ResponseEntity<>(opportunity, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(savedOpportunity, HttpStatus.OK);
		}
    }
	
	
	@RequestMapping(method=RequestMethod.POST, value="/opportunities/{opportunityId}/lock/{lock}")
    public ResponseEntity<Object> updateOpportunityLock(@PathVariable long opportunityId, @PathVariable boolean lock) {
		int success = opportunityService.lockOpportunity(opportunityId, lock);
		if(success == 1) {
			return new ResponseEntity<>(success, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CustomError("Opportunity lock unchanged"), HttpStatus.BAD_REQUEST);
		}
    }
	
	@RequestMapping(method=RequestMethod.PUT, value="/opportunities/{opportunityId}/servicetypes/{serviceTypeId}")
    public ResponseEntity<Object> updateOpportunityWithLoadedActivities(@PathVariable Long opportunityId, @PathVariable int serviceTypeId) {
		Opportunity opportunity = opportunityService.fetchOpportunity(opportunityId);
		if (opportunityId <= 0 || serviceTypeId <= 0) {
			return new ResponseEntity<>(new CustomError("Invalid id's"), HttpStatus.BAD_REQUEST);
		} else {
			List<Activity> preLoadedActivities = templateDataService.fetchActivitiesByServiceTypeId(serviceTypeId);
			List<OpportunityActivity> opportunityActivities = opportunityActivityService.saveOpportunityActivity(preLoadedActivities, opportunityId);
			if(opportunityActivities == null) {
				return new ResponseEntity<>(new CustomError("Failed to preload templates"), HttpStatus.BAD_REQUEST);
			} else {
				opportunity.setOpportunityActivities(opportunityActivities);
				return new ResponseEntity<>(opportunity, HttpStatus.OK);
			}
		}
    }
	

}
