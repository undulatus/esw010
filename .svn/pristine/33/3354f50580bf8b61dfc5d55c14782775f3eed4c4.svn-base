package com.pointwest.workforce.planner.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="resource_schedule")
public class WeeklyFTE {
	
	@EmbeddedId
	private WeeklyFTEKey key;
	
	@Column(name="resource_schedule_fte")
	private double resourceScheduleFTE;

	public WeeklyFTEKey getKey() {
		return key;
	}

	public void setKey(WeeklyFTEKey key) {
		this.key = key;
	}

	public double getResourceScheduleFTE() {
		return resourceScheduleFTE;
	}

	public void setResourceScheduleFTE(double resourceScheduleFTE) {
		this.resourceScheduleFTE = resourceScheduleFTE;
	}
	

}
