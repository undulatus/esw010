package com.pointwest.workforce.planner.service;

import com.pointwest.workforce.planner.domain.Opportunity;
import com.pointwest.workforce.planner.domain.Version;

public interface VersionService {

	public int saveVersion(Version version);

	/**
	 * This will discard any current data for the opportunity with the opportunityId
	 * and load the opportunity from the version (with versionId)
	 */	
	public Opportunity fetchAndLoadOpportunityVersion(long versionId, long opportunityId);
}
