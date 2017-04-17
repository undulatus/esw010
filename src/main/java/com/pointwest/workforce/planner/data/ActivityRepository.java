package com.pointwest.workforce.planner.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pointwest.workforce.planner.domain.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Integer> {

	@Query(value= 
			" SELECT a.activity_id, a.activity_name, a.activity_is_custom" +
			" FROM ref_activity a" +
			" LEFT JOIN tmpl_service_line_activity sla ON a.activity_id = sla.activity_id" +
			" WHERE sla.service_line_id =?1"
			, nativeQuery=true)
	public List<Activity> findActivitiesByServiceLineId(int serviceLineId);
	
}
