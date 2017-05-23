package com.pointwest.workforce.planner.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pointwest.workforce.planner.domain.Version;

public interface VersionRepository extends CrudRepository<Version, Long> {

	public List<Version> findByOpportunityId(Long opportunityId);

}
