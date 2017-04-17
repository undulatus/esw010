package com.pointwest.workforce.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.Group;
import com.pointwest.workforce.planner.service.ReferenceDataService;
import com.pointwest.workforce.planner.service.TemplateDataService;

@RestController
public class GroupController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@Autowired
	TemplateDataService templateDataService;
	
	@RequestMapping("/groups")
    public List<Group> fetchAllGroup() {
       return referenceDataService.fetchAllGroup();
    }
	
	@RequestMapping("/groups/{groupId}")
    public Group fetchGroup(@PathVariable int groupId) {
       return referenceDataService.fetchGroup(groupId);
    }
	

}
