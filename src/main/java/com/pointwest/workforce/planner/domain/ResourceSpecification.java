package com.pointwest.workforce.planner.domain;

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
@Table(name="resource_specification")
public class ResourceSpecification {
	
	public ResourceSpecification() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="resource_specification_id")
	private int resourceSpecificationId;
	
	@OneToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	@OneToOne
	@JoinColumn(name="practice_id")
	private Practice practice;
	
	@OneToOne
	@JoinColumn(name="pay_level_id")
	private PayLevel payLevel;
	
	@Column(name="resource_specification_is_billable",columnDefinition="INT(1)")
	private boolean isBillable;
	
//	@ManyToOne
//	@JoinColumn(name="opportunity_activity_id")
	private long opportunityActivityId;
	
	@OneToMany(mappedBy = "key.resourceSpecificationId", cascade = CascadeType.ALL)
	private List<WeeklyFTE> resourceSchedule;
	

	public int getResourceSpecificationId() {
		return resourceSpecificationId;
	}

	public void setResourceSpecificationId(int resourceSpecificationId) {
		this.resourceSpecificationId = resourceSpecificationId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Practice getPractice() {
		return practice;
	}

	public void setPractice(Practice practice) {
		this.practice = practice;
	}

	public PayLevel getPayLevel() {
		return payLevel;
	}

	public void setPayLevel(PayLevel payLevel) {
		this.payLevel = payLevel;
	}

	public boolean isBillable() {
		return isBillable;
	}

	public void setBillable(boolean isBillable) {
		this.isBillable = isBillable;
	}

	public long getOpportunityActivityId() {
		return opportunityActivityId;
	}

	public void setOpportunityActivityId(long opportunityActivityId) {
		this.opportunityActivityId = opportunityActivityId;
	}

	public List<WeeklyFTE> getResourceSchedule() {
		return resourceSchedule;
	}

	public void setResourceSchedule(List<WeeklyFTE> resourceSchedule) {
		this.resourceSchedule = resourceSchedule;
	}
	
	
	
}
