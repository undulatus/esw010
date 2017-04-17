package com.pointwest.workforce.planner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.data.OpportunityCollaboratorRepository;
import com.pointwest.workforce.planner.data.SystemRoleAccessRepository;
import com.pointwest.workforce.planner.service.AccessService;

@Service
public class AccessServiceImpl implements AccessService {

	@Autowired
	private SystemRoleAccessRepository systemRoleAccessRepository;
	
	@Autowired
	private OpportunityCollaboratorRepository opportunityCollaboratorRepository;
	
	@Override
	public boolean isSystemRoleAllowedAccess(String systemRole, String module, String action) {
		boolean allowed = systemRoleAccessRepository.countSystemRoleAllowedAccess(systemRole, module, action) > 0 ? true : false;
		return allowed;
	}

	@Override
	public boolean hasPermissionToEditOpportunity(long opportunityId, String username, String permission) {
		boolean allowed = opportunityCollaboratorRepository.countUsernameWithEdit(opportunityId, username, permission) > 0 ? true : false;
		return allowed;
	}

	
}
