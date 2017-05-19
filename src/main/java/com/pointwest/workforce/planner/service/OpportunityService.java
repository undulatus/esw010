package com.pointwest.workforce.planner.service;

import java.util.List;

import com.pointwest.workforce.planner.domain.Opportunity;
import com.pointwest.workforce.planner.ui.adapter.OpportunityDashboardProjection;

public interface OpportunityService {

	public List<Opportunity> fetchAllOpportunities();

	public Opportunity fetchOpportunity(long opportunityId);

	public Opportunity saveOpportunity(Opportunity opportunity);

	public Opportunity updateOpportunity(Opportunity opportunity, Long opportunityId);

	public List<Opportunity> fetchOpportunityList();
	
	public int lockOpportunity(long opportunityId, boolean lock);
	
	public List<Opportunity> fetchNotOwnedOpportunitiesByUsername(String username);
	
	public List<OpportunityDashboardProjection> fetchOpportunitiesByUsername(String username);

	public List<OpportunityDashboardProjection> fetchSharedOpportunitiesByUsername(String username);
}
