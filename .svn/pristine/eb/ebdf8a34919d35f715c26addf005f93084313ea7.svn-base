package com.pointwest.workforce.planner.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="opportunity_activity")
public class OpportunityActivity {
	
	public OpportunityActivity() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="opportunity_activity_id")
	private int opportunityActivityId;
	
	@OneToOne
	@JoinColumn(name="activity_id")
	private Activity activity;
	
	//@ManyToOne
	//@JoinColumn(name="opportunity_id")
	private long opportunityId;

	@Column(name="opportunity_activity_duration")
	private Double durationInWeeks;
	
	@Column(name="opportunity_activity_start_date")
	private Timestamp activityStartDate;
	
	@OneToMany(mappedBy = "opportunityActivityId", cascade = CascadeType.ALL)
	private List<ResourceSpecification> resourceSpecificationList;

	public int getOpportunityActivityId() {
		return opportunityActivityId;
	}

	public void setOpportunityActivityId(int opportunityActivityId) {
		this.opportunityActivityId = opportunityActivityId;
	}

	public long getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(long opportunityId) {
		this.opportunityId = opportunityId;
	}

	public Double getDurationInWeeks() {
		return durationInWeeks;
	}

	public void setDurationInWeeks(Double durationInWeeks) {
		this.durationInWeeks = durationInWeeks;
	}

	public Timestamp getActivityStartDate() {
		return activityStartDate;
	}

	public void setActivityStartDate(Timestamp activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	public List<ResourceSpecification> getResourceSpecificationList() {
		return resourceSpecificationList;
	}

	public void setResourceSpecificationList(List<ResourceSpecification> resourceSpecificationList) {
		this.resourceSpecificationList = resourceSpecificationList;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	
}
