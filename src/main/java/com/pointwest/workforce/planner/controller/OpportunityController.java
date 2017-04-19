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
	
	@RequestMapping("/opportunities")
    public List<Opportunity> fetchAllOpportunities() {
       return opportunityService.fetchAllOpportunities();
    }
	
	@RequestMapping("/opportunities/{opportunityId}")
    public Opportunity fetchOpportunity(@PathVariable int opportunityId) {
       return opportunityService.fetchOpportunity(opportunityId);
    }
	
	@RequestMapping("/users/{username}/opportunities")
    public List<Opportunity> fetchOpportunity(@PathVariable String username) {
       return opportunityService.fetchOpportunitiesByUsername(username);
    }
	
	@RequestMapping("/users/{username}/opportunities/other")
    public List<Opportunity> fetchNotOwnedOpportunity(@PathVariable String username) {
       return opportunityService.fetchNotOwnedOpportunitiesByUsername(username);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/opportunities")
    public ResponseEntity<Opportunity> saveOpportunity(@RequestBody(required=false) Opportunity opportunity) {
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
			return new ResponseEntity<>(opportunity, HttpStatus.BAD_REQUEST);
		} else {
			if(isNew) {
				return new ResponseEntity<Opportunity>(savedOpportunity, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Opportunity>(savedOpportunity, HttpStatus.OK);
			}
		}
    }
	
	@RequestMapping(method=RequestMethod.PUT, value="/opportunities/{opportunityId}")
    public ResponseEntity<Object> updateOpportunity(@PathVariable Long opportunityId, @RequestBody(required=true) Opportunity opportunity) {
		Opportunity savedOpportunity = null;
		Long idInRequestBody = opportunity.getOpportunityId();
		if ( (idInRequestBody != null) && ((idInRequestBody.compareTo(opportunityId)) != 0) ) {
			//unmatched object and url id's
			return new ResponseEntity<>("Unmatched ID's in RequestBody and URL", HttpStatus.BAD_REQUEST);
		}
		savedOpportunity = opportunityService.updateOpportunity(opportunity, opportunityId);
		if(savedOpportunity==null) {
			return new ResponseEntity<>(opportunity, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(savedOpportunity, HttpStatus.OK);
		}
    }
	
	
	@RequestMapping(method=RequestMethod.POST, value="/opportunities/{opportunityId}/lock/{lock}")
    public void updateOpportunityLock(@PathVariable long opportunityId, @PathVariable boolean lock) {
		opportunityService.lockOpportunity(opportunityId, lock);
    }
	
	@RequestMapping(method=RequestMethod.PUT, value="/opportunities/{opportunityId}/servicetypes/{serviceTypeId}")
    public ResponseEntity<Object> updateOpportunityWithLoadedActivities(@PathVariable Long opportunityId, @PathVariable int serviceTypeId) {
		Opportunity opportunity = opportunityService.fetchOpportunity(opportunityId);
		if (opportunityId <= 0 || serviceTypeId <= 0) {
			return new ResponseEntity<>("Invalid id's", HttpStatus.BAD_REQUEST);
		} else {
			List<Activity> preLoadedActivities = templateDataService.fetchActivitiesByServiceTypeId(serviceTypeId);
			List<OpportunityActivity> opportunityActivities = opportunityActivityService.saveOpportunityActivity(preLoadedActivities, opportunityId);
			if(opportunityActivities == null) {
				return new ResponseEntity<>("Failed to preload templates", HttpStatus.BAD_REQUEST);
			} else {
				opportunity.setOpportunityActivities(opportunityActivities);
				return new ResponseEntity<>(opportunity, HttpStatus.OK);
			}
		}
    }
	

}
