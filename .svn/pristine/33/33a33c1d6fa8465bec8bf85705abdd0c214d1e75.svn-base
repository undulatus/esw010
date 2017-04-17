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

import com.pointwest.workforce.planner.domain.Opportunity;
import com.pointwest.workforce.planner.service.OpportunityService;

@RestController
public class OpportunityController {
	
	@Autowired
	OpportunityService opportunityService;
	
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
		} else if(opportunity.getOpportunityId() == 0) {
			savedOpportunity = opportunityService.saveOpportunity(opportunity);
			isNew = true;
		} else {
			savedOpportunity = opportunityService.saveOpportunity(opportunity);
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
	
	
	@RequestMapping(method=RequestMethod.POST, value="/opportunities/{opportunityId}/lock/{lock}")
    public void updateOpportunityLock(@PathVariable long opportunityId, @PathVariable boolean lock) {
		opportunityService.lockOpportunity(opportunityId, lock);
    }

}
