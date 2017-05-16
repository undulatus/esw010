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

import com.pointwest.workforce.planner.domain.CustomError;
import com.pointwest.workforce.planner.domain.OpportunityCollaborator;
import com.pointwest.workforce.planner.service.OpportunityCollaboratorService;
import com.pointwest.workforce.planner.ui.adapter.CollaboratorsAdapter;
import com.pointwest.workforce.planner.ui.domain.Collaborators;

@RestController
public class OpportunityCollaboratorController {
	
	@Autowired
	OpportunityCollaboratorService opportunityCollaboratorService;
	
	@Autowired
	CollaboratorsAdapter collaboratorsAdapter; 

	@RequestMapping(method=RequestMethod.GET, value="/opportunities/{opportunityId}/opportunitycollaborators")
    public ResponseEntity<Object> fetchOpportunityCollaborators(@PathVariable Long opportunityId) {
		List<OpportunityCollaborator> opportunityCollaborators = opportunityCollaboratorService.fetchOpportunityCollaborators(opportunityId);
		if(opportunityCollaborators == null || opportunityCollaborators.isEmpty()) {
			return new ResponseEntity<>(new CustomError("No Opportunity Collaborators retrieved"), HttpStatus.NOT_FOUND);
		} else {
			Collaborators collaborators = collaboratorsAdapter.transform(opportunityCollaborators, opportunityId);
			return new ResponseEntity<>(collaborators, HttpStatus.OK);
		}
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/opportunities/{opportunityId}/opportunitycollaborators/{permission}")
    public ResponseEntity<Object> saveOpportunityCollaborator(@RequestBody(required=true) List<String> usernames, @PathVariable Long opportunityId, @PathVariable String permission) {
		List<OpportunityCollaborator> opportunityCollaborators = this.opportunityCollaboratorService.saveOpportunityCollaborator(usernames, opportunityId, permission);
		return new ResponseEntity<>(opportunityCollaborators, HttpStatus.OK);
    }
	
	
}
