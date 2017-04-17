package com.pointwest.workforce.planner.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sys_system_role_access")
public class SystemRoleAccess implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemRoleAccess() {
		super();
	}
	
	@EmbeddedId
	private SystemRoleAccessKey instance;
	
	public SystemRoleAccessKey getInstance() {
		return instance;
	}

	public void setInstance(SystemRoleAccessKey instance) {
		this.instance = instance;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Embeddable
	public class SystemRoleAccessKey implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Column(name="system_role")
		private String systemRole;

		@Column(name="system_role_access_module")
		private String module;
		
		@Column(name="system_role_access_action")
		private String action;

		public String getSystemRole() {
			return systemRole;
		}

		public void setSystemRole(String systemRole) {
			this.systemRole = systemRole;
		}

		public String getModule() {
			return module;
		}

		public void setModule(String module) {
			this.module = module;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}
	}

}
