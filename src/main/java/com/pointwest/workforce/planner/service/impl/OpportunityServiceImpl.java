package com.pointwest.workforce.planner.service.impl;

import java.sql.Date;
import java.time.LocalDate;
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
import com.pointwest.workforce.planner.ui.adapter.OpportunityDashboardProjection;
import com.pointwest.workforce.planner.util.DateUtil;

@Service
public class OpportunityServiceImpl implements OpportunityService {

	@Autowired
	public OpportunityRepository opportunityRepository;

	@Autowired
	public OpportunityLockEntityRepository opportunityEntityRepository;

	@Value("${opportunity.documentstatus.locked}")
	private String LOCKED;

	@Value("${opportunity.documentstatus.unlocked}")
	private String UNLOCKED;
	
	@Value("${month.to.week.multiplier}")
	private Integer WEEKSINMONTH;

	private static final Logger log = LoggerFactory.getLogger(WorkforcePlannerApplication.class);

	@Override
	public List<Opportunity> fetchAllOpportunities() {
		List<Opportunity> opportunities = new ArrayList<Opportunity>();
		opportunityRepository.findAll().forEach(opportunities::add);
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

	@Override
	public Opportunity updateOpportunity(Opportunity opportunity, Long opportunityId) {
		// if id not supplied in body then set it
		if (opportunity.getOpportunityId() == null)
			opportunity.setOpportunityId(opportunityId);
		// do not save null values but set the previous values into it
		Opportunity previousOpportunity = opportunityRepository.findOne(opportunityId);
		if (opportunity.getOpportunityName() == null)
			opportunity.setOpportunityName(previousOpportunity.getOpportunityName());
		if (opportunity.getBusinessUnit() == null)
			opportunity.setBusinessUnit(previousOpportunity.getBusinessUnit());
		if (opportunity.getServiceType() == null)
			opportunity.setServiceType(previousOpportunity.getServiceType());
		if (opportunity.getDurationGranularity() == null)
			opportunity.setDurationGranularity(previousOpportunity.getDurationGranularity());
		if (opportunity.getDurationInWeeks() == null)
			opportunity.setDurationInWeeks(previousOpportunity.getDurationInWeeks());
		if (opportunity.getProjectStartDate() == null)
			opportunity.setProjectStartDate(previousOpportunity.getProjectStartDate());
		if (opportunity.getOpportunityStatus() == null)
			opportunity.setOpportunityStatus(previousOpportunity.getOpportunityStatus());
		if (opportunity.getDocumentStatus() == null)
			opportunity.setDocumentStatus(previousOpportunity.getDocumentStatus());
		if (opportunity.getClientName() == null)
			opportunity.setClientName(previousOpportunity.getClientName());
		if (opportunity.getProjectAlias() == null)
			opportunity.setProjectAlias(previousOpportunity.getProjectAlias());
		if (opportunity.getUser() == null)
			opportunity.setUser(previousOpportunity.getUser());

		return opportunityRepository.save(opportunity);
	}

	@Override
	public List<Opportunity> fetchOpportunityList() {
		List<Opportunity> opportunityList = opportunityRepository.findOpportunityList();
		return opportunityList;
	}

	// @Override
	public boolean lockOpportunityPre(long opportunityId, boolean lock) {
		String documentStatus = (lock == true ? LOCKED : UNLOCKED);
		opportunityRepository.updateOpportunityDocumentStatus(opportunityId, documentStatus);
		return true;
	}

	@Override
	public int lockOpportunity(long opportunityId, boolean lock) {
		String documentStatus = (lock == true ? UNLOCKED : LOCKED);
		OpportunityLockEntity opportunity = new OpportunityLockEntity(opportunityId, documentStatus);
		OpportunityLockEntity saved = opportunityEntityRepository.save(opportunity);
		return saved != null ? 1 : 0;
	}

	@Override
	public List<OpportunityDashboardProjection> fetchOpportunitiesByUsername(String username) {
		List<OpportunityDashboardProjection> oppList;
		oppList = opportunityRepository.findByUserUsername(username.trim());
		/*for (Opportunity opp : oppList) {

			log.debug("my opp : " + opp.toString());
		}*/
		return oppList;

	}

	@Override
	public List<Opportunity> fetchNotOwnedOpportunitiesByUsername(String username) {
		List<Opportunity> oppList = new ArrayList<Opportunity>();
		oppList = opportunityRepository.findNotOwnedOpportunitiesByUserUsername(username.trim());
		for (Opportunity opp : oppList) {

			log.debug("other opp : " + opp.toString());
		}
		return oppList;

	}

	@Override
	public List<OpportunityDashboardProjection> fetchSharedOpportunitiesByUsername(String username) {
		List<OpportunityDashboardProjection> oppList;
		oppList = opportunityRepository.findByOpportunityCollaboratorsKeyUsername(username);
		return oppList;
	}

	@Override
	public Opportunity updateOpportunityDates(Long opportunityId) {
		Opportunity opportunity = opportunityRepository.findOne(opportunityId);
		LocalDate startDate = opportunity.getProjectStartDate().toLocalDate();
		int durationInWeeks = opportunity.getDurationInWeeks().intValue();
		
		Date endDate = DateUtil.adjustDate(startDate, durationInWeeks, WEEKSINMONTH);
		opportunity.setProjectEndDate(endDate);
		
		return opportunityRepository.save(opportunity);
	}
}
