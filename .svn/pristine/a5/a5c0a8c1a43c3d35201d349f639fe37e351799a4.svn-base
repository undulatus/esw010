package com.pointwest.workforce.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.Practice;
import com.pointwest.workforce.planner.service.ReferenceDataService;
import com.pointwest.workforce.planner.service.TemplateDataService;

@RestController
public class PracticeController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@Autowired
	TemplateDataService templateDataService;
	
	@RequestMapping("/practices")
    public List<Practice> fetchAllPractice() {
       return referenceDataService.fetchAllPractice();
    }
	
	@RequestMapping("/practices/{practiceId}")
    public Practice fetchPractice(@PathVariable int practiceId) {
       return referenceDataService.fetchPractice(practiceId);
    }
	
	@RequestMapping("/roles/{roleId}/practices")
    public List<Practice> fetchPracticesByRoleId(@PathVariable int roleId) {
       return templateDataService.fetchPracticesByRoleId(roleId);
    }

}
