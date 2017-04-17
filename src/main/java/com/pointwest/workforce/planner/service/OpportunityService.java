package com.pointwest.workforce.planner.service;

import java.util.List;

import com.pointwest.workforce.planner.domain.Opportunity;

public interface OpportunityService {

	public List<Opportunity> fetchAllOpportunities();

	public Opportunity fetchOpportunity(long opportunityId);

	public Opportunity saveOpportunity(Opportunity opportunity);

	//public int updateOpportunity(Opportunity opportunity);

	public List<Opportunity> fetchOpportunityList();
	
	public int lockOpportunity(long opportunityId, boolean lock);
	
	public List<Opportunity> fetchOpportunitiesByUsername(String username);
	
	public List<Opportunity> fetchNotOwnedOpportunitiesByUsername(String username);
}
