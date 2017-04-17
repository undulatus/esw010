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
	public OpportunityActivity fetchOpportunityActivity(long opportunityActivityId) {
		return opportunityActivityRepository.findOne(opportunityActivityId);
		
	}

	@Override
	public OpportunityActivity saveOpportunityActivity(OpportunityActivity opportunityActivity) {
		return opportunityActivityRepository.save(opportunityActivity);
	}
	
	
	

	
}
