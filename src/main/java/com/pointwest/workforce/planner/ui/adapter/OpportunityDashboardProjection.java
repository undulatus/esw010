package com.pointwest.workforce.planner.ui.adapter;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.pointwest.workforce.planner.domain.OpportunityCollaborator;

public interface OpportunityDashboardProjection {
	
	
	public Long getOpportunityId();

	public String getOpportunityName();
	
	@Value("#{target.businessUnit != null ? target.businessUnit.businessUnitName : null }")
	public String getBusinessUnitName();
	
	@Value("#{target.serviceType != null ? target.serviceType.serviceTypeName : null }")
	public String getServiceTypeName();
	
	public Date getProjectStartDate();

	public String getOpportunityStatus();

	public String getDocumentStatus();

	public String getClientName();
	
	public List<OpportunityCollaborator> getOpportunityCollaborators();
	
}
