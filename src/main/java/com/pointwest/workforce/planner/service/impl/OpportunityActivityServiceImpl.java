package com.pointwest.workforce.planner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.data.OpportunityActivityRepository;
import com.pointwest.workforce.planner.domain.OpportunityActivity;
import com.pointwest.workforce.planner.service.OpportunityActivityService;

@Service
public class OpportunityActivityServiceImpl implements OpportunityActivityService {
	
	@Autowired
	public OpportunityActivityRepository opportunityActivityRepository;

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
		return opportunityActivityRepository.save(opportunityActivity);
	}

	@Override
	public int deleteOpportunityActivity(Long opportunityActivityId) {
		int initialCount = opportunityActivityRepository.countByOpportunityActivityId(opportunityActivityId);
		opportunityActivityRepository.delete(opportunityActivityId);
		int postDeleteCount = opportunityActivityRepository.countByOpportunityActivityId(opportunityActivityId);
		return initialCount - postDeleteCount;
	}
	

	
}
