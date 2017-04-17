package com.pointwest.workforce.planner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_activity")
public class Activity {
	
	public Activity() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="activity_id")
	private int activityId;
	
	@Column(name="activity_name")
	private String activityName;
	
	@Column(name="activity_is_custom", columnDefinition="INT(1)")
	private boolean isCustom;

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public boolean isCustom() {
		return isCustom;
	}

	public void setCustom(boolean isCustom) {
		this.isCustom = isCustom;
	}
	
	
}
