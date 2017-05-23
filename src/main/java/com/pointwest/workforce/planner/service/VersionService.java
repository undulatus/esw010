package com.pointwest.workforce.planner.service;

import java.util.List;

import com.pointwest.workforce.planner.domain.Version;
import com.pointwest.workforce.planner.domain.Version.VersionKey;

public interface VersionService {

	public Version saveVersion(Long opportunityId, String versionName, String versionData);

	public List<Version> fetchVersions(Long opportunityId);
	
	public Version fetchOpportunityVersion(VersionKey key);
	
	/**
	 * This will discard any current data for the opportunity with the opportunityId
	 * and load the opportunity from the version (with versionId)
	 *//*	
	public Version fetchAndApplyOpportunityVersion(Long versionId);*/
	
}
