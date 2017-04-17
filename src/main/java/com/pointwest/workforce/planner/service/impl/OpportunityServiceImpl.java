package com.pointwest.workforce.planner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.WorkforcePlannerApplication;
import com.pointwest.workforce.planner.data.OpportunityLockEntityRepository;
import com.pointwest.workforce.planner.data.OpportunityRepository;
import com.pointwest.workforce.planner.domain.Opportunity;
import com.pointwest.workforce.planner.domain.OpportunityLockEntity;
import com.pointwest.workforce.planner.service.OpportunityService;
import com.pointwest.workforce.planner.service.ReferenceDataService;

@Service
public class OpportunityServiceImpl implements OpportunityService {
	
	@Autowired
	public OpportunityRepository opportunityRepository;
	
	@Autowired
	public OpportunityLockEntityRepository opportunityEntityRepository;
	
	@Autowired
	public ReferenceDataService referenceDataService;
	
	@Value("${opportunity.documentstatus.locked}")
	private String LOCKED;
	
	@Value("${opportunity.documentstatus.unlocked}")
	private String UNLOCKED;
	
	private static final Logger log = LoggerFactory.getLogger(WorkforcePlannerApplication.class);
	
	@Override
	public List<Opportunity> fetchAllOpportunities() {
		List<Opportunity> opportunities = new ArrayList<Opportunity>(); 
		opportunityRepository.findAll().forEach(opportunities::add);
//		
//		for(Opportunity opportunity : opportunities) {
//			log.debug(opportunity.getOpportunityName() + " MC: " + opportunity.getMarketCircle().getMarketCircleId());
//		}
		return opportunities;
	}
	
	@Override
	public Opportunity fetchOpportunity(long opportunityId) {
		return opportunityRepository.findOne(opportunityId);
	}
	
	@Override
	public Opportunity saveOpportunity(Opportunity opportunity) {
		Opportunity saved = opportunityRepository.save(opportunity);
		return saved;
	}
	
	/*@Override
	public int updateOpportunity(Opportunity opportunity) {
		Opportunity saved = opportunityRepository.save(opportunity);
		
		return saved != null ?  1 : 0;
	}*/

	@Override
	public List<Opportunity> fetchOpportunityList() {
		List<Opportunity> opportunityList = opportunityRepository.findOpportunityList();
		return opportunityList;
	}

	//@Override
	public boolean lockOpportunityPre(long opportunityId, boolean lock) {
		String documentStatus = (lock == true ? LOCKED : UNLOCKED);
		opportunityRepository.updateOpportunityDocumentStatus(opportunityId, documentStatus);
		return true;
	}
	
	@Override
	public int lockOpportunity(long opportunityId, boolean lock) {
		String documentStatus = (lock == true ? LOCKED : UNLOCKED);
		OpportunityLockEntity opportunity = new OpportunityLockEntity(opportunityId, documentStatus);
		OpportunityLockEntity saved = opportunityEntityRepository.save(opportunity);
		return saved != null ?  1 : 0;
	}

	@Override
	public List<Opportunity> fetchOpportunitiesByUsername(String username) {
		List<Opportunity> oppList = new ArrayList<Opportunity>();
		oppList = opportunityRepository.findByUserUsername(username.trim());
		for(Opportunity opp : oppList){

			log.debug("my opp : " + opp.toString());
		}
		return opportunityRepository.findByUserUsername(username.trim());
	}
	
	@Override
	public List<Opportunity> fetchNotOwnedOpportunitiesByUsername(String username) {
		List<Opportunity> oppList = new ArrayList<Opportunity>();
		oppList = opportunityRepository.findByUserUsername(username.trim());
		for(Opportunity opp : oppList){

			log.debug("other opp : " + opp.toString());
		}
		return opportunityRepository.findNotOwnedOpportunitiesByUserUsername(username.trim());
	}
}
