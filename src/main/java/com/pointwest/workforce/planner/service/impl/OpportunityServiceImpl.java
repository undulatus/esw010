package com.pointwest.workforce.planner.service.impl;

import java.math.BigInteger;
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
import com.pointwest.workforce.planner.domain.WeeklyFTEKey;
import com.pointwest.workforce.planner.service.OpportunityActivityService;
import com.pointwest.workforce.planner.service.OpportunityService;
import com.pointwest.workforce.planner.service.ResourceSpecificationService;
import com.pointwest.workforce.planner.service.WeeklyFTEService;
import com.pointwest.workforce.planner.ui.adapter.OpportunityDashboardProjection;
import com.pointwest.workforce.planner.util.DateUtil;

@Service
public class OpportunityServiceImpl implements OpportunityService {

	@Autowired
	public OpportunityRepository opportunityRepository;

	@Autowired
	public OpportunityLockEntityRepository opportunityEntityRepository;
	
	//bmab for regrouping
	@Autowired
	OpportunityActivityService opportunityActivityService;
	
	@Autowired
	ResourceSpecificationService resourceSpecificationService;
	
	@Autowired
	WeeklyFTEService weeklyFTEService;

	@Value("${opportunity.documentstatus.locked}")
	private String LOCKED;

	@Value("${opportunity.documentstatus.unlocked}")
	private String UNLOCKED;
	
	@Value("${month.to.week.multiplier}")
	private Integer WEEKSINMONTH;
	
	/*@Value("${granularity.week}")
	private String WEEKLY;
	
	@Value("${granularity.month}")
	private String MONTHLY;*/

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
		/*if(opportunity.getDurationGranularity() == WEEKLY) {
			opportunity.setDurationInWeeks(opportunity.getDurationInWeeks() * WEEKSINMONTH);
		}*/
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
		if (opportunity.getDurationInWeeks() == null) {
			opportunity.setDurationInWeeks(previousOpportunity.getDurationInWeeks()); 
		} else {
			//conversion weekly to monthly currently front naghahandle
			/*if(opportunity.getDurationGranularity() == WEEKLY) {
				opportunity.setDurationInWeeks(opportunity.getDurationInWeeks() * WEEKSINMONTH);
			}*/
			//handle possibly affected fte's
			try {
				handleTruncatedFte(opportunityId,opportunity.getDurationInWeeks(), previousOpportunity.getDurationInWeeks());
			} catch (Exception e) {
				//rethrow next time
				log.error("error in handling truncated ftes " + e.getMessage());
			}
		}
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
		if (opportunity.getUsername() == null)
			opportunity.setUsername(previousOpportunity.getUsername());
		if (opportunity.getProjectEndDate() == null) opportunity.setProjectEndDate(previousOpportunity.getProjectEndDate());
		if (opportunity.getLastModifiedDate() == null) opportunity.setLastModifiedDate(previousOpportunity.getLastModifiedDate());
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
		oppList = opportunityRepository.findByUsername(username.trim());
		/*for (Opportunity opp : oppList) {

			log.debug("my opp : " + opp.toString());
		}*/
		return oppList;

	}

	@Override
	public List<Opportunity> fetchNotOwnedOpportunitiesByUsername(String username) {
		List<Opportunity> oppList = new ArrayList<Opportunity>();
		oppList = opportunityRepository.findNotOwnedOpportunitiesByUsername(username.trim());
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
		
		int durationInWeeks = 0;

		if(opportunity.getDurationInWeeks() != null) {
		durationInWeeks = opportunity.getDurationInWeeks().intValue();
		}
		
		Date endDate = DateUtil.adjustDateExclusive(startDate, durationInWeeks, WEEKSINMONTH);
		opportunity.setProjectEndDate(endDate);
		
		return opportunityRepository.save(opportunity);
	}

	@Override
	public boolean isUsernameOwner(long opportunityId, String username) {
		boolean isOwner = opportunityRepository.countUsernameWithOpportunityId(opportunityId, username) > 0 ? true : false;
		return isOwner;
	}
	
	//bmab candidate for regrouping
	private void datePostUpdateProcessing(Long resourceSpecificationId) {
		//handle role start date, duration, total fte all in weeks 
		resourceSpecificationService.updateResourceSpecificationDates(resourceSpecificationId);
		//handle activity start date, duration in weeks
		opportunityActivityService.updateOpportunityActivityDates(resourceSpecificationId);
		
	}
	
	private void handleTruncatedFte(Long opportunityId, Double newDurationInWeeks, Double previousDurationInWeeks) throws Exception {
		
		if(newDurationInWeeks < previousDurationInWeeks) {
			log.debug("now truncating fte schedule that is out of duration range");
			List<BigInteger> rsIdsraw = opportunityRepository.findResourceSpecificationIdsUnderOpportunityId(opportunityId);
			WeeklyFTEKey key = null;
			
			//get duration range
			Long truncateFromWeek = Math.round(newDurationInWeeks) + 1;
			Long truncateToWeek = Math.round(previousDurationInWeeks);
			
			int fteDeleteCount = 0;
			Long resourceSpecificationId = null;
			log.debug("under opp id : " + opportunityId);
			for(BigInteger rsId : rsIdsraw) {
				resourceSpecificationId = rsId.longValue();
				log.debug(" \t rsid : " + resourceSpecificationId);
				for(Long week=truncateFromWeek; week <= truncateToWeek; week ++) {
					key = new WeeklyFTEKey(resourceSpecificationId, week);
					fteDeleteCount = fteDeleteCount + weeklyFTEService.deleteWeeklyFTE(key);
				}
				//bmab candidate
				datePostUpdateProcessing(resourceSpecificationId);
			}
			log.debug("all deleted fte count = " + fteDeleteCount);
			
		} else {
			log.debug("no truncated fte schedule due to duration change");
		}
		
	}
}
