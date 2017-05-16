package com.pointwest.workforce.planner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.data.OpportunityCollaboratorRepository;
import com.pointwest.workforce.planner.domain.OpportunityCollaborator;
import com.pointwest.workforce.planner.service.OpportunityCollaboratorService;

@Service
public class OpportunityCollaboratorServiceImpl implements OpportunityCollaboratorService {
	
	@Autowired
	public OpportunityCollaboratorRepository opportunityCollaboratorRepository;
	
	private static final Logger log = LoggerFactory.getLogger(OpportunityCollaboratorServiceImpl.class);

	@Override
	public List<OpportunityCollaborator> fetchOpportunityCollaborators(Long opportunityId) {
		List<OpportunityCollaborator> opportunityCollaborators = new ArrayList<OpportunityCollaborator>(); 
		opportunityCollaboratorRepository.findOpportunityCollaboratorsByKeyOpportunityId(opportunityId)
		.forEach(opportunityCollaborators::add);
		log.debug("opportunityId: " + opportunityId + " collaborators count = " + opportunityCollaborators.size());
		return opportunityCollaborators;
	}

	@Override
	public List<OpportunityCollaborator> saveOpportunityCollaborator(List<String> usernames, Long opportunityId,
			String permission) {
		List<OpportunityCollaborator> opportunityCollaborators = new ArrayList<OpportunityCollaborator>();
		for(String username : usernames) {
			opportunityCollaborators.add(new OpportunityCollaborator(username, opportunityId, permission));
		}
		return (List<OpportunityCollaborator>) opportunityCollaboratorRepository.save(opportunityCollaborators); 
	}

	@Override
	public int deleteByOpportunityIdAndPermission(Long opportunityId, String permission) {
		int initialCount = opportunityCollaboratorRepository.countByKeyOpportunityId(opportunityId);
		log.debug("collaborator init count " + initialCount);
		opportunityCollaboratorRepository.deleteByKeyOpportunityIdAndPermission(opportunityId, permission);
		int postDeleteCount = opportunityCollaboratorRepository.countByKeyOpportunityId(opportunityId);
		log.debug("collaborator post delete count " + postDeleteCount);
		return initialCount - postDeleteCount;
	}

	
}
