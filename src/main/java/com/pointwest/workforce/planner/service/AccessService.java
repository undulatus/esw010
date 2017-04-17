package com.pointwest.workforce.planner.service;

public interface AccessService {

	public boolean isSystemRoleAllowedAccess(String systemRole, String module, String action);
	
	public boolean hasPermissionToEditOpportunity(long opportunityId, String username, String permission);
	
}
