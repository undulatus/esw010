package com.pointwest.workforce.planner.service.impl;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Value("${collaborator.permission.edit}")
	private String EDIT;
	
	@Override
	public boolean isSystemRoleAllowedAccess(String systemRole, String module, String action) {
		boolean allowed = systemRoleAccessRepository.countSystemRoleAllowedAccess(systemRole, module, action) > 0 ? true : false;
		return allowed;
	}

	@Override
	public boolean hasPermission(long opportunityId, String username, String permission) {
		boolean allowed = opportunityCollaboratorRepository.countUsernameWithEdit(opportunityId, username, permission) > 0 ? true : false;
		return allowed;
	}
	
	@Override
	public boolean hasPermissionToEdit(long opportunityId, String username) {
		boolean allowed = opportunityCollaboratorRepository.countUsernameWithEdit(opportunityId, username, EDIT) > 0 ? true : false;
		
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Set<String> authorities= AuthorityUtils.authorityListToSet(auth.getAuthorities());
		
		for (Iterator<String> it = authorities.iterator(); it.hasNext(); ) {
	        String role = it.next();
	        
	    }*/
		
		return allowed;
	}

	
}
