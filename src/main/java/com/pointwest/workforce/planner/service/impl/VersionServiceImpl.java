package com.pointwest.workforce.planner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.data.VersionRepository;
import com.pointwest.workforce.planner.domain.Opportunity;
import com.pointwest.workforce.planner.domain.Version;
import com.pointwest.workforce.planner.service.VersionService;

@Service
public class VersionServiceImpl implements VersionService {
	
	@Autowired
	VersionRepository versionRepository;

	@Override
	public Version saveVersion(Version version) {
		return versionRepository.save(version);
	}

	@Override
	public Opportunity fetchAndLoadOpportunityVersion(long versionId, long opportunityId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
