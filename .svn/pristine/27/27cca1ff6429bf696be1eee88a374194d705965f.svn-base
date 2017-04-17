package com.pointwest.workforce.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.PayLevel;
import com.pointwest.workforce.planner.service.ReferenceDataService;
import com.pointwest.workforce.planner.service.TemplateDataService;

@RestController
public class PayLevelController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@Autowired
	TemplateDataService templateDataService;
	
	@RequestMapping("/paylevels")
    public List<PayLevel> fetchAllPayLevel() {
       return referenceDataService.fetchAllPayLevel();
    }
	
	@RequestMapping("/paylevels/{payLevelId}")
    public PayLevel fetchActivity(@PathVariable int payLevelId) {
       return referenceDataService.fetchPayLevel(payLevelId);
    }
	

}
