package com.pointwest.workforce.planner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_service_line")
public class ServiceType {
	
	public ServiceType() {
		super();
	}

	@Id
	@Column(name="service_line_id")
	private int serviceTypeId;
	
	@Column(name="service_line_name")
	private String serviceTypeName;

	public int getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public String getServiceTypeName() {
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}
	
	
}
