package com.pointwest.workforce.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.CustomError;
import com.pointwest.workforce.planner.domain.OrganizationRole;
import com.pointwest.workforce.planner.service.ReferenceDataService;
import com.pointwest.workforce.planner.service.TemplateDataService;

@RestController
public class OrganizationRoleController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@Autowired
	TemplateDataService templateDataService;
	
	@RequestMapping(method=RequestMethod.GET, value="/orgroles")
    public ResponseEntity<Object> fetchAllOrgRole() {
		List<OrganizationRole> orgRoles = referenceDataService.fetchAllOrgRole();
		if(orgRoles == null || orgRoles.isEmpty()) {
			return new ResponseEntity<>(new CustomError("No Organization Roles retrieved"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(orgRoles, HttpStatus.OK);
		}
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/orgroles/{orgroleId}")
    public ResponseEntity<Object> fetchOrgRole(@PathVariable int orgroleId) {
		OrganizationRole orgRole = referenceDataService.fetchOrgRole(orgroleId);
       if(orgRole == null) {
			return new ResponseEntity<>(new CustomError("Organization Role not found"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(orgRole, HttpStatus.OK);
		}
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/servicetypes/{serviceTypeId}/orgroles")
    public ResponseEntity<Object> fetchOrgRolesByServiceTypeId(@PathVariable int serviceTypeId) {
       List<OrganizationRole> orgRoles = templateDataService.fetchOrgRolesByServiceTypeId(serviceTypeId);
		if(orgRoles == null) {
			return new ResponseEntity<>(new CustomError("No organization roles retrieved for the given service type"), HttpStatus.NOT_FOUND);
		} else {
		   return new ResponseEntity<>(orgRoles, HttpStatus.OK);
		}
    }

}
