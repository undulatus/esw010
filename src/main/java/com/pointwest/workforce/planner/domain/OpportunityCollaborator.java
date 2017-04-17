package com.pointwest.workforce.planner.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="opportunity_collaborator")
public class OpportunityCollaborator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpportunityCollaborator() {
		super();
	}
	
	@EmbeddedId
	private OpportunityCollaboratorKey key;
	
	@Column(name="opportunity_collaborator_permission")
	private String permission;
	
	public OpportunityCollaboratorKey getKey() {
		return key;
	}
	
	public void setKey(OpportunityCollaboratorKey key) {
		this.key = key;
	}
	
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	@Embeddable
	public class OpportunityCollaboratorKey implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Column(name="opportunity_id")
		private Long opportunityId;

		@Column(name="username")
		private String username;
		
	}

}
