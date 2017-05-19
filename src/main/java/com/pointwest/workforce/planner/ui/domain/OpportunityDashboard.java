package com.pointwest.workforce.planner.ui.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.pointwest.workforce.planner.domain.OpportunityCollaborator;

public class OpportunityDashboard implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpportunityDashboard() {
		super();
	}
	
	public OpportunityDashboard(Long opportunityId, String opportunityName,
			String businessUnitName, String serviceTypeName,
			Date projectStartDate, String opportunityStatus, String documentStatus,
			String clientName, List<OpportunityCollaborator> opportunityCollaborators) {
		super();
		this.opportunityId = opportunityId;
		this.opportunityName = opportunityName;
		this.businessUnitName = businessUnitName;
		this.serviceTypeName = serviceTypeName;
		this.projectStartDate = projectStartDate;
		this.opportunityStatus = opportunityStatus;
		this.documentStatus = documentStatus;
		this.clientName = clientName;
		this.opportunityCollaborators = opportunityCollaborators;
		//this.user = user;
	}

	private Long opportunityId;
	
	private String opportunityName;
	
	private String businessUnitName;
	
	private String serviceTypeName;
	
	private Date projectStartDate;
	
	private String opportunityStatus;
	
	private String documentStatus;
	
	private String clientName;
	
	private List<OpportunityCollaborator> opportunityCollaborators;
	
	private String userPermission;

	public Long getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(Long opportunityId) {
		this.opportunityId = opportunityId;
	}

	public String getOpportunityName() {
		return opportunityName;
	}

	public void setOpportunityName(String opportunityName) {
		this.opportunityName = opportunityName;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public String getOpportunityStatus() {
		return opportunityStatus;
	}

	public void setOpportunityStatus(String opportunityStatus) {
		this.opportunityStatus = opportunityStatus;
	}

	public String getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getBusinessUnitName() {
		return businessUnitName;
	}
	
	public String getServiceTypeName() {
		return serviceTypeName;
	}

	public List<OpportunityCollaborator> getOpportunityCollaborators() {
		return opportunityCollaborators;
	}

	public String getUserPermission() {
		return this.userPermission;
	}

	public void setUserPermission(String username) {
		for (OpportunityCollaborator collaborator : this.opportunityCollaborators) {
			if(collaborator.getKey().getUsername().equals(username)) {
				this.userPermission = collaborator.getPermission();
				break;
			}
		}
	}
	
	
}
