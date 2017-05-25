package com.pointwest.workforce.planner.ui.adapter;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.pointwest.workforce.planner.domain.OpportunityCollaborator;

public interface OpportunityDashboardProjection {
	
	
	public Long getOpportunityId();

	public String getOpportunityName();
	
	@Value("#{target.businessUnit.businessUnitName}")
	public String getBusinessUnitName();
	
	@Value("#{target.serviceType.serviceTypeName}")
	public String getServiceTypeName();
	
	public Date getProjectStartDate();

	public String getOpportunityStatus();

	public String getDocumentStatus();

	public String getClientName();
	
	public List<OpportunityCollaborator> getOpportunityCollaborators();
	
}
