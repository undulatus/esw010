package com.pointwest.workforce.planner.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pointwest.workforce.planner.domain.Version;
import com.pointwest.workforce.planner.domain.Version.VersionKey;

public interface VersionRepository extends CrudRepository<Version, VersionKey> {

	public List<Version> findByKeyOpportunityId(Long opportunityId);

}
