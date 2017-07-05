package com.pointwest.workforce.planner.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
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
		Version version = new Version(opportunityId, versionName, versionDescription, versionData, true, true, false);
		versionRepository.noActiveVersion(opportunityId);
		return versionRepository.save(version);
	}
	
	@Override
	public Version updateVersion(Long opportunityId, String versionName, String versionNewName, String versionDescription, String versionData,
			Boolean isDeleted) {
		Version version = new Version(opportunityId, versionName, versionDescription, versionData, false, null, isDeleted);
		Version prevVersion = versionRepository.findOne(version.getKey());
		if (version.getDateCreated() == null) version.setDateCreated(prevVersion.getDateCreated());
		if (version.getIsActive() == null) version.setIsActive(prevVersion.getIsActive());
		if (version.getUsername() == null) version.setUsername(prevVersion.getUsername());
		if (version.getIsDeleted() == null) version.setIsDeleted(prevVersion.getIsDeleted());
		if (version.getIsDeleted() == true) {
			versionNewName = versionName + "*" + Timestamp.from(Instant.now()).toString();
		}
		if(versionNewName != null) {
			versionRepository.renameVersion(opportunityId, versionName, versionNewName);
			
		}
		if(versionNewName != null && isDeleted != null && isDeleted == true) {
			version.getKey().setVersionName(versionNewName);
			return versionRepository.save(version);
		} /*else if(versionNewName != null && isDeleted == null) {
			return versionRepository.save(version);
		} */
		return new Version(opportunityId, versionNewName, versionDescription, null, false);
		
	}

	@Override
	public List<VersionNoDataProjection> fetchVersions(Long opportunityId, Boolean isDeleted) {
		return versionRepository.findByKeyOpportunityIdAndIsDeletedOrderByDateCreatedDesc(opportunityId, isDeleted);
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
