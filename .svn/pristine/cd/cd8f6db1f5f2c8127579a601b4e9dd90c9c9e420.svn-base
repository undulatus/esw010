package com.pointwest.workforce.planner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_service_line")
public class ServiceLine {
	
	public ServiceLine() {
		super();
	}

	@Id
	@Column(name="service_line_id")
	private int serviceLineId;
	
	@Column(name="service_line_name")
	private String serviceLineName;

	public int getServiceLineId() {
		return serviceLineId;
	}

	public void setServiceLineId(int serviceLineId) {
		this.serviceLineId = serviceLineId;
	}

	public String getServiceLineName() {
		return serviceLineName;
	}

	public void setServiceLineName(String serviceLineName) {
		this.serviceLineName = serviceLineName;
	}
	
	
}
