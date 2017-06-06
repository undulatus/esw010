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
	public Version saveVersion(Long opportunityId, String versionName, String versionDescription, String versionData) {
		Version version = new Version(opportunityId, versionName, versionDescription, versionData, true, true);
		versionRepository.noActiveVersion(opportunityId);
		return versionRepository.save(version);
	}
	
	@Override
	public Version updateVersion(Long opportunityId, String versionName, String versionDescription, String versionData) {
		Version version = new Version(opportunityId, versionName, versionDescription, versionData, false);
		Version prevVersion = versionRepository.findOne(version.getKey());
		if (version.getDateCreated() == null) version.setDateCreated(prevVersion.getDateCreated());
		if (version.getIsActive() == null) version.setIsActive(prevVersion.getIsActive());
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
	
	@Override
	public void activateVersion(Long opportunityId, String versionName) {
		versionRepository.noActiveVersion(opportunityId);
		versionRepository.activateVersion(opportunityId, versionName);
	}

	/*@Override
	public Version fetchAndApplyOpportunityVersion(Long versionId) {
		
	}*/
	
	
}
