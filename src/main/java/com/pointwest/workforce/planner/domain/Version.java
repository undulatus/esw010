package com.pointwest.workforce.planner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="version")
public class Version {
	
	public Version() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="version_id")
	private int versionId;
	
	@Column(name="version_name")
	private String versionName;
	
	@Column(name="opportunity_id")
	private long opportunityId;
	
	@Column(name="version_data")
	private String versionData;

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public long getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(long opportunityId) {
		this.opportunityId = opportunityId;
	}

	public String getVersionData() {
		return versionData;
	}

	public void setVersionData(String versionData) {
		this.versionData = versionData;
	}

	
}
