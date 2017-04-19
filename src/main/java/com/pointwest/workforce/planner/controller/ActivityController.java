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
import com.pointwest.workforce.planner.service.ReferenceDataService;
import com.pointwest.workforce.planner.service.TemplateDataService;

@RestController
public class ActivityController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@Autowired
	TemplateDataService templateDataService;
	
	@RequestMapping("/activities")
    public List<Activity> fetchAllActivity() {
       return referenceDataService.fetchAllActivity();
    }
	
	@RequestMapping("/activities/{activityId}")
    public Activity fetchActivity(@PathVariable int activityId) {
       return referenceDataService.fetchActivity(activityId);
    }
	
	@RequestMapping("/servicetypes/{serviceTypeId}/activities")
    public List<Activity> fetchActivitiesByServiceTypeId(@PathVariable int serviceTypeId) {
       return templateDataService.fetchActivitiesByServiceTypeId(serviceTypeId);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/activities")
    public ResponseEntity<Activity> saveCustomActivity(@RequestBody(required=true) String activityName) {
		Activity customActivity = new Activity(activityName, true);
		customActivity = referenceDataService.addActivity(customActivity);
		if(customActivity==null) {
			return new ResponseEntity<Activity>(customActivity, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Activity>(customActivity, HttpStatus.CREATED);
		}
    }

}
