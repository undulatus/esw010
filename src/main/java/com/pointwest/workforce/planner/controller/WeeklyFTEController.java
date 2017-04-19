package com.pointwest.workforce.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.WeeklyFTE;
import com.pointwest.workforce.planner.domain.WeeklyFTEKey;
import com.pointwest.workforce.planner.service.WeeklyFTEService;

@RestController
public class WeeklyFTEController {
	
	@Autowired
	WeeklyFTEService weeklyFTEService;
	
	@RequestMapping("/weeklyftes")
    public List<WeeklyFTE> fetchAllOpportunities() {
       return weeklyFTEService.fetchAllWeeklyFTEs();
    }
	
	@RequestMapping("/resourcespecifications/{resourceSpecificationId}/weeklyftes/{resourceScheduleWeekNumber}")
    public ResponseEntity<Object> fetchWeeklyFTE(@PathVariable Long resourceSpecificationId, @PathVariable Long resourceScheduleWeekNumber) {
		if( !(resourceSpecificationId instanceof Long) || !(resourceScheduleWeekNumber instanceof Long) ) {
			return new ResponseEntity<>("Invalid Id's", HttpStatus.BAD_REQUEST);
		} else if(resourceSpecificationId <= 0 || resourceScheduleWeekNumber <= 0) {
			return new ResponseEntity<>("Invalid Id's", HttpStatus.BAD_REQUEST);
		}
		else {
			WeeklyFTEKey key = new WeeklyFTEKey(resourceSpecificationId, resourceScheduleWeekNumber);
			WeeklyFTE weeklyFTE = weeklyFTEService.fetchWeeklyFTE(key);
			return new ResponseEntity<>(weeklyFTE, HttpStatus.OK);
		}
    }
	

	@RequestMapping(method=RequestMethod.POST, value="/resourcespecifications/{resourceSpecificationId}/weeklyftes/{resourceScheduleWeekNumber}")
    public ResponseEntity<Object> saveWeeklyFTE(@PathVariable Long resourceSpecificationId, @PathVariable Long resourceScheduleWeekNumber, 
    		@RequestBody(required=true) Double FTE) {
		WeeklyFTE savedWeeklyFTE = null;
		if( !(resourceSpecificationId instanceof Long) || !(resourceScheduleWeekNumber instanceof Long) ) {
			return new ResponseEntity<>("Invalid Id's", HttpStatus.BAD_REQUEST);
		} else if(resourceSpecificationId <= 0 || resourceScheduleWeekNumber <= 0) {
			return new ResponseEntity<>("Invalid Id's", HttpStatus.BAD_REQUEST);
		} else if( !(FTE instanceof Double) ) {
			return new ResponseEntity<>("Invalid Body", HttpStatus.BAD_REQUEST);
		} else if(FTE > 1 || FTE < 0) {
			return new ResponseEntity<>("Invalid Body", HttpStatus.BAD_REQUEST);
		} else {
			WeeklyFTE weeklyFTE = new WeeklyFTE(resourceSpecificationId, resourceScheduleWeekNumber, FTE);
			savedWeeklyFTE = weeklyFTEService.saveWeeklyFTE(weeklyFTE);
		}
		if(savedWeeklyFTE==null) {
			return new ResponseEntity<>("nothing saved", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(savedWeeklyFTE, HttpStatus.OK);
		}
		
    }
	
	@RequestMapping(method=RequestMethod.PUT, value="/resourcespecifications/{resourceSpecificationId}/weeklyftes/{resourceScheduleWeekNumber}")
    public ResponseEntity<Object> updateWeeklyFTE(@PathVariable Long resourceSpecificationId, @PathVariable Long resourceScheduleWeekNumber, 
    		@RequestBody(required=true) Double FTE) {
		WeeklyFTE savedWeeklyFTE = null;
		if( !(resourceSpecificationId instanceof Long) || !(resourceScheduleWeekNumber instanceof Long) ) {
			return new ResponseEntity<>("Invalid Id's", HttpStatus.BAD_REQUEST);
		} else if(resourceSpecificationId <= 0 || resourceScheduleWeekNumber <= 0) {
			return new ResponseEntity<>("Invalid Id's", HttpStatus.BAD_REQUEST);
		} else if( !(FTE instanceof Double) ) {
			return new ResponseEntity<>("Invalid Body", HttpStatus.BAD_REQUEST);
		} else if(FTE > 1 || FTE < 0) {
			return new ResponseEntity<>("Invalid Body", HttpStatus.BAD_REQUEST);
		} else {
			WeeklyFTE weeklyFTE = new WeeklyFTE(resourceSpecificationId, resourceScheduleWeekNumber, FTE);
			savedWeeklyFTE = weeklyFTEService.saveWeeklyFTE(weeklyFTE);
		}
		if(savedWeeklyFTE==null) {
			return new ResponseEntity<>("nothing saved", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(savedWeeklyFTE, HttpStatus.OK);
		}
    }
	
	@RequestMapping(method=RequestMethod.DELETE, value="/resourcespecifications/{resourceSpecificationId}/weeklyftes/{resourceScheduleWeekNumber}")
    public ResponseEntity<Object> deleteWeeklyFTE(@PathVariable Long resourceSpecificationId, @PathVariable Long resourceScheduleWeekNumber) {
		int deleteCount = 0;
		if( !(resourceSpecificationId instanceof Long) || !(resourceScheduleWeekNumber instanceof Long) ) {
			return new ResponseEntity<>("Invalid Id's", HttpStatus.BAD_REQUEST);
		} else if(resourceSpecificationId <= 0 || resourceScheduleWeekNumber <= 0) {
			return new ResponseEntity<>("Invalid Id's", HttpStatus.BAD_REQUEST);
		} else {
			WeeklyFTEKey key = new WeeklyFTEKey(resourceSpecificationId, resourceScheduleWeekNumber);
			deleteCount = weeklyFTEService.deleteWeeklyFTE(key);
		}
		if(deleteCount > 0) {
			return new ResponseEntity<>(deleteCount, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(deleteCount, HttpStatus.BAD_REQUEST);
		}
    }
	
	
}
