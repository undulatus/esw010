package com.pointwest.workforce.planner.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pointwest.workforce.planner.domain.OpportunityCollaborator;
import com.pointwest.workforce.planner.ui.domain.Collaborators;

@Component
public class CollaboratorsAdapter extends Collaborators {
	
	@Value("${collaborator.permission.edit}")
	private String EDIT;
	
	@Value("${collaborator.permission.view}")
	private String VIEW;
	
	public CollaboratorsAdapter() {
		super();
	}
	
	public CollaboratorsAdapter(List<OpportunityCollaborator> opportunityCollaborators, Long opportunityId) {
		this.initial(opportunityCollaborators, opportunityId);
	}

	private void initial(List<OpportunityCollaborator> opportunityCollaborators, Long opportunityId) {
		this.createTheUserLists(opportunityCollaborators);
		this.createTheOpportunityId(opportunityId);
	}
	
	public Collaborators transform(List<OpportunityCollaborator> opportunityCollaborators, Long opportunityId) {
		this.createTheUserLists(opportunityCollaborators);
		this.createTheOpportunityId(opportunityId);
		return this;
	}
	
	private void createTheUserLists(List<OpportunityCollaborator> opportunityCollaborators) {
		List<String> editUsers = new ArrayList<String>();
		List<String> viewUsers = new ArrayList<String>();
		for(OpportunityCollaborator opportunityCollaborator : opportunityCollaborators) {
			if(opportunityCollaborator.getPermission().equals(EDIT)) {
				editUsers.add(opportunityCollaborator.getKey().getUsername());
			} else if (opportunityCollaborator.getPermission().equals(VIEW)){
				viewUsers.add(opportunityCollaborator.getKey().getUsername());
			}
		}
		super.setUsersWithEdit(editUsers);
		super.setUsersWithView(viewUsers);
	}
	
	private void createTheOpportunityId(Long opportunityId) {
		this.setOpportunityId(opportunityId);
	}
}
