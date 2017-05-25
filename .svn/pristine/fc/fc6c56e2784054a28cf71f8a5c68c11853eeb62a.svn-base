package com.pointwest.workforce.planner.data;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pointwest.workforce.planner.domain.OpportunityCollaborator;
import com.pointwest.workforce.planner.domain.OpportunityCollaborator.OpportunityCollaboratorKey;

public interface OpportunityCollaboratorRepository extends CrudRepository<OpportunityCollaborator, OpportunityCollaboratorKey> {

	@Query(value= 
			//" SELECT system_role, system_role_access_module, system_role_access_action," +
			" SELECT count(username)" +
			" FROM workforce_planner.opportunity_collaborator" +
			" WHERE opportunity_id = ?1" +
			" AND username = ?2" +
			" AND opportunity_collaborator_permission = ?3"
			, nativeQuery=true)
	public int countUsernameWithEdit(long opportunityId, String username, String permission);
	
	public int countByKeyOpportunityId(Long opportunityId);
	
	public List<OpportunityCollaborator> findOpportunityCollaboratorsByKeyOpportunityId(Long opportunityId);
	
	@Modifying
	@Transactional
	@Query(value= 
		" DELETE FROM opportunity_collaborator " +
		" WHERE opportunity_id = ?1 " +
		" AND opportunity_collaborator_permission = ?2"
		, nativeQuery=true)
	public void deleteByKeyOpportunityIdAndPermission(Long opportunityId, String permission);
	
}
