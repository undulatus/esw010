package com.pointwest.workforce.planner.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pointwest.workforce.planner.domain.WeeklyFTE;
import com.pointwest.workforce.planner.domain.WeeklyFTEKey;

public interface WeeklyFTERepository extends CrudRepository<WeeklyFTE, WeeklyFTEKey> {
	
	public int countByKey(WeeklyFTEKey weeklyFTEKey);
	
	public List<WeeklyFTE> findWeeklyFTEsByKeyResourceSpecificationId(Long resourceSpecificationId);
	
}
