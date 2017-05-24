package com.pointwest.workforce.planner.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="version")
public class Version implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Version() {
		super();
	}
	
	public Version(Long opportunityId, String versionName, String versionData) {
		Version.VersionKey key = new Version.VersionKey();
		key.setOpportunityId(opportunityId);
		key.setVersionName(versionName);
		this.key = key;
		this.versionData = versionData;
		this.setDateCreated(Timestamp.from(Instant.now()));
	}

	@EmbeddedId
	private VersionKey key;
	
	@Column(name="version_data")
	private String versionData;
	
	@Column(name="version_date_created")
	private Timestamp dateCreated;

	public VersionKey getKey() {
		return key;
	}

	public void setKey(VersionKey key) {
		this.key = key;
	}

	public String getVersionData() {
		return versionData;
	}

	public void setVersionData(String versionData) {
		this.versionData = versionData;
	}
	
	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Embeddable
	public static class VersionKey implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	
		@Column(name="version_name")
		private String versionName;
		
		@Column(name="opportunity_id")
		private Long opportunityId;

		public String getVersionName() {
			return versionName;
		}

		public void setVersionName(String versionName) {
			this.versionName = versionName;
		}
		
		public Long getOpportunityId() {
			return opportunityId;
		}

		public void setOpportunityId(Long opportunityId) {
			this.opportunityId = opportunityId;
		}

		@Override
		public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || this.getClass() != o.getClass()) return false;
	        VersionKey that = (VersionKey) o;
	        if (opportunityId != null? !opportunityId.equals(that.getOpportunityId()) : that.getOpportunityId() != null) return false;
	        if (versionName != null? !versionName.equals(that.getVersionName()) : that.getVersionName() != null) return false;
	        return true;
	    }

		@Override
	    public int hashCode() {
	        int result;
	        result = (versionName != null? versionName.hashCode() : 0);
	        result = 31 * result + (opportunityId !=null? opportunityId.hashCode() : 0);
	        return result;
	    }  
		
	}
}
