package com.pointwest.workforce.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.Role;
import com.pointwest.workforce.planner.service.ReferenceDataService;
import com.pointwest.workforce.planner.service.TemplateDataService;

@RestController
public class RoleController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@Autowired
	TemplateDataService templateDataService;
	
	@RequestMapping("/roles")
    public List<Role> fetchAllRole() {
       return referenceDataService.fetchAllRole();
    }
	
	@RequestMapping("/roles/{roleId}")
    public Role fetchRole(@PathVariable int roleId) {
       return referenceDataService.fetchRole(roleId);
    }
	
	@RequestMapping("/servicetypes/{serviceTypeId}/roles")
    public List<Role> fetchRolesByServiceTypeId(@PathVariable int serviceTypeId) {
       return templateDataService.fetchRolesByServiceTypeId(serviceTypeId);
    }

}
