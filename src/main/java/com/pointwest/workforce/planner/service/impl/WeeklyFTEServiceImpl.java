package com.pointwest.workforce.planner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.data.WeeklyFTERepository;
import com.pointwest.workforce.planner.domain.WeeklyFTE;
import com.pointwest.workforce.planner.domain.WeeklyFTEKey;
import com.pointwest.workforce.planner.service.WeeklyFTEService;

@Service
public class WeeklyFTEServiceImpl implements WeeklyFTEService {
	
	@Autowired
	public WeeklyFTERepository weeklyFTERepository;

	@Override
	public List<WeeklyFTE> fetchAllWeeklyFTEs() {
		List<WeeklyFTE> opportunityActivies = new ArrayList<WeeklyFTE>(); 
		weeklyFTERepository.findAll().forEach(opportunityActivies::add);
		return opportunityActivies;
	}

	@Override
	public WeeklyFTE fetchWeeklyFTE(WeeklyFTEKey key) {
		return weeklyFTERepository.findOne(key);
	}

	@Override
	public WeeklyFTE saveWeeklyFTE(WeeklyFTE weeklyFTE) {
		return weeklyFTERepository.save(weeklyFTE);
	}
	
	@Override
	public WeeklyFTE updateWeeklyFTE(WeeklyFTE weeklyFTE,
			WeeklyFTEKey weeklyFTEKey) {
		//if id not supplied in request body then set it
		if(weeklyFTE.getKey() == null) weeklyFTE.setKey(weeklyFTEKey);
		//do not save null values but set the previous values into it
		//WeeklyFTE previousWeeklyFTE = weeklyFTERepository.findOne(weeklyFTEKey);
		//if(weeklyFTE.getResourceScheduleFTE() == null) weeklyFTE.setActivity(previousWeeklyFTE.getActivity());
		
		return weeklyFTERepository.save(weeklyFTE);
	}

	@Override
	public int deleteWeeklyFTE(WeeklyFTEKey weeklyFTEKey) {
		//int initialCount = weeklyFTERepository.countByWeeklyFTEKey(weeklyFTEKey);
		weeklyFTERepository.delete(weeklyFTEKey);
		//int postDeleteCount = weeklyFTERepository.countByWeeklyFTEKey(weeklyFTEKey);
		return 1;
	}
	

	
}
