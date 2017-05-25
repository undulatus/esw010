package com.pointwest.workforce.planner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.data.OpportunityActivityRepository;
import com.pointwest.workforce.planner.domain.Activity;
import com.pointwest.workforce.planner.domain.OpportunityActivity;
import com.pointwest.workforce.planner.service.OpportunityActivityService;

@Service
public class OpportunityActivityServiceImpl implements OpportunityActivityService {
	
	@Autowired
	public OpportunityActivityRepository opportunityActivityRepository;
	
	private static final Logger log = LoggerFactory.getLogger(OpportunityActivityServiceImpl.class);

	@Override
	public List<OpportunityActivity> fetchAllOpportunityActivities() {
		List<OpportunityActivity> opportunityActivies = new ArrayList<OpportunityActivity>(); 
		opportunityActivityRepository.findAll().forEach(opportunityActivies::add);
		return opportunityActivies;
	}

	@Override
	public OpportunityActivity fetchOpportunityActivity(Long opportunityActivityId) {
		return opportunityActivityRepository.findOne(opportunityActivityId);
		
	}

	@Override
	public OpportunityActivity saveOpportunityActivity(OpportunityActivity opportunityActivity) {
		return opportunityActivityRepository.save(opportunityActivity);
	}
	
	@Override
	public OpportunityActivity updateOpportunityActivity(OpportunityActivity opportunityActivity,
			Long opportunityActivityId) {
		//if id not supplied in request body then set it
		if(opportunityActivity.getOpportunityActivityId() == null) opportunityActivity.setOpportunityActivityId(opportunityActivityId);
		//do not save null values but set the previous values into it
		OpportunityActivity previousOpportunityActivity = opportunityActivityRepository.findOne(opportunityActivityId);
		if(opportunityActivity.getActivity() == null) opportunityActivity.setActivity(previousOpportunityActivity.getActivity());
		if(opportunityActivity.getActivityStartDate() == null) opportunityActivity.setActivityStartDate(previousOpportunityActivity.getActivityStartDate());
		if(opportunityActivity.getDurationInWeeks() == null) opportunityActivity.setDurationInWeeks(previousOpportunityActivity.getDurationInWeeks());
		if(opportunityActivity.getOpportunityId() == null) opportunityActivity.setOpportunityId(previousOpportunityActivity.getOpportunityId());;
		return opportunityActivityRepository.save(opportunityActivity);
	}

	@Override
	public int deleteOpportunityActivity(Long opportunityActivityId) {
		log.debug("MCI >> deleteOpportunityActivity id : " + opportunityActivityId);
		int initialCount = opportunityActivityRepository.countByOpportunityActivityId(opportunityActivityId);
		log.debug("before delete count " + initialCount);
		opportunityActivityRepository.delete(opportunityActivityId);
		int postDeleteCount = opportunityActivityRepository.countByOpportunityActivityId(opportunityActivityId);
		log.debug("after delete count " + postDeleteCount);
		log.debug("deleted count " + (initialCount - postDeleteCount));
		log.debug("MCO >> deleteOpportunityActivity id : " + opportunityActivityId);
		return initialCount - postDeleteCount;
	}

	@Override
	public List<OpportunityActivity> saveOpportunityActivity(List<Activity> activities, Long opportunityId) {
		List<OpportunityActivity> opportunityActivities = new ArrayList<OpportunityActivity>();
		for(Activity activity : activities) {
			opportunityActivities.add(new OpportunityActivity(activity, opportunityId));
		}
		opportunityActivityRepository.save(opportunityActivities);
		return (List<OpportunityActivity>) opportunityActivityRepository.save(opportunityActivities);
	}
	

	
}
