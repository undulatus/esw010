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

import com.pointwest.workforce.planner.domain.OpportunityActivity;
import com.pointwest.workforce.planner.service.OpportunityActivityService;

@RestController
public class OpportunityActivityController {
	
	@Autowired
	OpportunityActivityService opportunityActivityService;
	
	@RequestMapping("/opportunityactivities")
    public List<OpportunityActivity> fetchAllOpportunities() {
       return opportunityActivityService.fetchAllOpportunityActivities();
    }
	
	@RequestMapping("/opportunityactivities/{opportunityActivityId}")
    public OpportunityActivity fetchOpportunityActivity(@PathVariable Long opportunityActivityId) {
       return opportunityActivityService.fetchOpportunityActivity(opportunityActivityId);
    }
	

	@RequestMapping(method=RequestMethod.POST, value="/opportunityactivities")
    public ResponseEntity<OpportunityActivity> saveOpportunityActivity(@RequestBody(required=false) OpportunityActivity opportunityActivity) {
		OpportunityActivity savedOpportunityActivity = null;
		boolean isNew = false;
		if(opportunityActivity==null) {
			savedOpportunityActivity = opportunityActivityService.saveOpportunityActivity(new OpportunityActivity());
			isNew = true;
		} else if(opportunityActivity.getOpportunityActivityId() == null) {
			savedOpportunityActivity = opportunityActivityService.saveOpportunityActivity(opportunityActivity);
			isNew = true;
		} else {
			savedOpportunityActivity = opportunityActivityService.updateOpportunityActivity(opportunityActivity, opportunityActivity.getOpportunityActivityId());
			isNew = false;
		}
		if(savedOpportunityActivity==null) {
			return new ResponseEntity<>(opportunityActivity, HttpStatus.BAD_REQUEST);
		} else {
			if(isNew) {
				return new ResponseEntity<OpportunityActivity>(savedOpportunityActivity, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<OpportunityActivity>(savedOpportunityActivity, HttpStatus.OK);
			}
		}
    }
	
	@RequestMapping(method=RequestMethod.PUT, value="/opportunityactivities/{opportunityActivityId}")
    public ResponseEntity<Object> updateOpportunityActivity(@PathVariable Long opportunityActivityId, @RequestBody OpportunityActivity opportunityActivity) {
		OpportunityActivity savedOpportunityActivity = null;
		Long idInRequestBody = opportunityActivity.getOpportunityActivityId();
		if ( (idInRequestBody != null) && ((idInRequestBody.compareTo(opportunityActivityId)) != 0) ) {
			//unmatched object and url id's
			return new ResponseEntity<>("Unmatched ID's in RequestBody and URL", HttpStatus.BAD_REQUEST);
		}
		savedOpportunityActivity = opportunityActivityService.updateOpportunityActivity(opportunityActivity, opportunityActivityId);
		if(savedOpportunityActivity==null) {
			return new ResponseEntity<>(opportunityActivity, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(savedOpportunityActivity, HttpStatus.OK);
		}
    }
	
	
}
