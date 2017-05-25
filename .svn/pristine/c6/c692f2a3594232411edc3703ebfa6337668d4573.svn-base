package com.pointwest.workforce.planner.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="opportunity")
@DynamicUpdate
public class Opportunity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Opportunity() {
		super();
	}
	
	public Opportunity(Long opportunityId) {
		super();
		this.opportunityId = opportunityId;
	}

	@Id
	@Column(name="opportunity_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long opportunityId;
	
	@Column(name="opportunity_name")
	private String opportunityName;
	
	@OneToOne
	@JoinColumn(name="business_unit_id")
	private BusinessUnit businessUnit;
	
	@OneToOne
	@JoinColumn(name="service_type_id")
	private ServiceType serviceType;
	
	@Column(name="opportunity_duration_granularity")
	private String durationGranularity;
	
	@Column(name="opportunity_duration_week")
	private Double durationInWeeks;
	
	@Column(name="opportunity_start_date")
	private Timestamp projectStartDate;
	
	@Column(name="opportunity_status")
	private String opportunityStatus;
	
	@Column(name="opportunity_document_status")
	private String documentStatus;
	
	@Column(name="opportunity_client_name")
	private String clientName;
	
	@Column(name="opportunity_project_alias")
	private String projectAlias;
	
	@OneToOne
	@JoinColumn(name="username")
	private User user;
	
	//@OneToMany(mappedBy = "opportunityId", cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "opportunityId")
	private List<OpportunityActivity> opportunityActivities;

	public Long getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(Long opportunityId) {
		this.opportunityId = opportunityId;
	}

	public String getOpportunityName() {
		return opportunityName;
	}

	public void setOpportunityName(String opportunityName) {
		this.opportunityName = opportunityName;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String getDurationGranularity() {
		return durationGranularity;
	}

	public void setDurationGranularity(String durationGranularity) {
		this.durationGranularity = durationGranularity;
	}

	public Double getDurationInWeeks() {
		return durationInWeeks;
	}

	public void setDurationInWeeks(Double durationInWeeks) {
		this.durationInWeeks = durationInWeeks;
	}

	public Timestamp getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Timestamp projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public String getOpportunityStatus() {
		return opportunityStatus;
	}

	public void setOpportunityStatus(String opportunityStatus) {
		this.opportunityStatus = opportunityStatus;
	}

	public String getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProjectAlias() {
		return projectAlias;
	}

	public void setProjectAlias(String projectAlias) {
		this.projectAlias = projectAlias;
	}

	public List<OpportunityActivity> getOpportunityActivities() {
		return opportunityActivities;
	}

	public void setOpportunityActivities(List<OpportunityActivity> opportunityActivities) {
		this.opportunityActivities = opportunityActivities;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	/*@JsonSerialize()
	public Opportunity getJson() {
		return this;
	}*/

	@Override
	public String toString() {
		return "Opportunity [opportunityId=" + opportunityId + ", opportunityName=" + opportunityName
				+ ", businessUnit=" + businessUnit + ", serviceType=" + serviceType + ", durationGranularity="
				+ durationGranularity + ", durationInWeeks=" + durationInWeeks + ", projectStartDate="
				+ projectStartDate + ", opportunityStatus=" + opportunityStatus + ", documentStatus=" + documentStatus
				+ ", clientName=" + clientName + ", projectAlias=" + projectAlias + ", user=" + user
				+ ", opportunityActivities=" + opportunityActivities + "]";
	}


}
