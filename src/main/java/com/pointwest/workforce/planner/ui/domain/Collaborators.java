package com.pointwest.workforce.planner.ui.domain;

import java.util.List;

public class Collaborators {
	
	private List<String> usersWithEdit;
	
	private List<String> usersWithView;
	
	private Long opportunityId;
	
	public Collaborators() {
		super();
	}
	
	public List<String> getUsersWithEdit() {
		return usersWithEdit;
	}

	public void setUsersWithEdit(List<String> usersWithEdit) {
		this.usersWithEdit = usersWithEdit;
	}

	public List<String> getUsersWithView() {
		return usersWithView;
	}

	public void setUsersWithView(List<String> usersWithView) {
		this.usersWithView = usersWithView;
	}

	public Long getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(Long opportunityId) {
		this.opportunityId = opportunityId;
	}


}
