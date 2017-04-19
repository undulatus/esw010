package com.pointwest.workforce.planner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_market_circle")
public class BusinessUnit {
	
	public BusinessUnit() {
		super();
	}

	@Id
	@Column(name="market_circle_id")
	private int businessUnitId;
	
	@Column(name="market_circle_name")
	private String businessUnitName;

	public int getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(int businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	
}
