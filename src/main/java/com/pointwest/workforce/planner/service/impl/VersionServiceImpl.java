package com.pointwest.workforce.planner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.data.VersionRepository;
import com.pointwest.workforce.planner.domain.Version;
import com.pointwest.workforce.planner.domain.Version.VersionKey;
import com.pointwest.workforce.planner.service.VersionService;
import com.pointwest.workforce.planner.ui.adapter.VersionNoDataProjection;

@Service
public class VersionServiceImpl implements VersionService {
	
	@Autowired
	VersionRepository versionRepository;

	@Override
	public Version saveVersion(Long opportunityId, String versionName, String versionData) {
		Version version = new Version(opportunityId, versionName, versionData);
		return versionRepository.save(version);
	}

	@Override
	public List<VersionNoDataProjection> fetchVersions(Long opportunityId) {
		return versionRepository.findByKeyOpportunityIdOrderByDateCreatedDesc(opportunityId);
	}
	
	@Override
	public Version fetchOpportunityVersion(VersionKey key) {
		return versionRepository.findOne(key);
	}

	/*@Override
	public Version fetchAndApplyOpportunityVersion(Long versionId) {
		
	}*/
	
	
}
