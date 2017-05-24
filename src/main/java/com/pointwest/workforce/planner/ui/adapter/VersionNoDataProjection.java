package com.pointwest.workforce.planner.ui.adapter;

import java.sql.Timestamp;

import com.pointwest.workforce.planner.domain.Version.VersionKey;

public interface VersionNoDataProjection {
	
	public VersionKey getKey();
	
	public Timestamp getDateCreated();

}
