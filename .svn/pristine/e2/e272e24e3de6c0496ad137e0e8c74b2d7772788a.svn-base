package com.pointwest.workforce.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.BusinessUnit;
import com.pointwest.workforce.planner.service.ReferenceDataService;

@RestController
public class BusinessUnitController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@RequestMapping("/businessunits")
    public List<BusinessUnit> fetchAllBusinessUnit() {
       return referenceDataService.fetchAllBusinessUnit();
    }
	
	@RequestMapping("/businessunits/{businessUnitId}")
    public BusinessUnit fetchBusinessUnit(@PathVariable int businessUnitId) {
       return referenceDataService.fetchBusinessUnit(businessUnitId);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/businessunits")
    public void addBusinessUnit(@RequestBody BusinessUnit businessUnit) {
		referenceDataService.addBusinessUnit(businessUnit);
    }
	
	@RequestMapping(method=RequestMethod.PUT, value="/businessunits/{businessUnitId}")
    public void updateBusinessUnit(@RequestBody BusinessUnit businessUnit, Integer businessUnitId) {
		referenceDataService.updateBusinessUnit(businessUnitId, businessUnit);
    }

}
