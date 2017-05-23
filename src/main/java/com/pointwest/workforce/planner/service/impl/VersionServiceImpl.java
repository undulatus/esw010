package com.pointwest.workforce.planner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.data.VersionRepository;
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
	public List<Version> fetchVersions(Long opportunityId) {
		return versionRepository.findByOpportunityId(opportunityId);
	}
	
	@Override
	public Version fetchOpportunityVersion(Long versionId) {
		return versionRepository.findOne(versionId);
	}

	/*@Override
	public Version fetchAndApplyOpportunityVersion(Long versionId) {
		
	}*/
	
	
}
