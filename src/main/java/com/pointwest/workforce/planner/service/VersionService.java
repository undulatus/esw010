package com.pointwest.workforce.planner.service;

import java.util.List;

import com.pointwest.workforce.planner.domain.Version;

public interface VersionService {

	public Version saveVersion(Version version);

	public List<Version> fetchVersions(Long opportunityId);
	
	public Version fetchOpportunityVersion(Long versionId);
	
	/**
	 * This will discard any current data for the opportunity with the opportunityId
	 * and load the opportunity from the version (with versionId)
	 *//*	
	public Version fetchAndApplyOpportunityVersion(Long versionId);*/
	
}
