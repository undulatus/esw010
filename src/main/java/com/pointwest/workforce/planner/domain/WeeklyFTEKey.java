package com.pointwest.workforce.planner.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WeeklyFTEKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="resource_specification_id")
	private Long resourceSpecificationId;
	
	@Column(name="resource_schedule_week_number")
	private Long resourceScheduleWeekNumber;

	public Long getResourceSpecificationId() {
		return resourceSpecificationId;
	}

	public void setResourceSpecificationId(Long resourceSpecificationId) {
		this.resourceSpecificationId = resourceSpecificationId;
	}

	public Long getResourceScheduleWeekNumber() {
		return resourceScheduleWeekNumber;
	}

	public void setResourceScheduleWeekNumber(Long resourceScheduleWeekNumber) {
		this.resourceScheduleWeekNumber = resourceScheduleWeekNumber;
	}
	
}
