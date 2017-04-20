package com.pointwest.workforce.planner.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.CustomError;
import com.pointwest.workforce.planner.domain.WeeklyFTE;
import com.pointwest.workforce.planner.domain.WeeklyFTEKey;
import com.pointwest.workforce.planner.service.WeeklyFTEService;

@RestController
public class WeeklyFTEController {
	
	@Autowired
	WeeklyFTEService weeklyFTEService;
	
	@Value("${month.to.week.multiplier}")
	private Long WEEKSINMONTH;
	
	@RequestMapping(method=RequestMethod.GET, value="/weeklyftes")
    public ResponseEntity<Object> fetchAllWeeklyFTE() {
       List<WeeklyFTE> weeklyFTEs = weeklyFTEService.fetchAllWeeklyFTEs();
       if(weeklyFTEs == null || weeklyFTEs.isEmpty()) {
			return new ResponseEntity<>(new CustomError("No Weekly FTEs retrieved"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(weeklyFTEs, HttpStatus.OK);
		}
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/resourcespecifications/{resourceSpecificationId}/weeklyftes/{resourceScheduleWeekNumber}")
    public ResponseEntity<Object> fetchWeeklyFTE(@PathVariable Long resourceSpecificationId, @PathVariable Long resourceScheduleWeekNumber) {
		if( !(resourceSpecificationId instanceof Long) || !(resourceScheduleWeekNumber instanceof Long) ) {
			return new ResponseEntity<>(new CustomError("Invalid Id's"), HttpStatus.BAD_REQUEST);
		} else if(resourceSpecificationId <= 0 || resourceScheduleWeekNumber <= 0) {
			return new ResponseEntity<>(new CustomError("Invalid Id's"), HttpStatus.BAD_REQUEST);
		}
		else {
			WeeklyFTEKey key = new WeeklyFTEKey(resourceSpecificationId, resourceScheduleWeekNumber);
			WeeklyFTE weeklyFTE = weeklyFTEService.fetchWeeklyFTE(key);
			if(weeklyFTE == null) {
				return new ResponseEntity<>(new CustomError("No weekly fte entry"), HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(weeklyFTE, HttpStatus.OK);
			}
		}
    }
	

	@RequestMapping(method=RequestMethod.POST, value="/resourcespecifications/{resourceSpecificationId}/weeklyftes/{granularity}/{resourceScheduleWeekNumber}")
    public ResponseEntity<Object> saveWeeklyFTE(@PathVariable Long resourceSpecificationId, @PathVariable String granularity, @PathVariable Long resourceScheduleWeekNumber, 
    		@RequestBody(required=true) Double FTE) {
		WeeklyFTE savedWeeklyFTE = null;
		if( !(resourceSpecificationId instanceof Long) || !(resourceScheduleWeekNumber instanceof Long) ) {
			return new ResponseEntity<>(new CustomError("Invalid Id's"), HttpStatus.BAD_REQUEST);
		} else if(resourceSpecificationId <= 0 || resourceScheduleWeekNumber <= 0) {
			return new ResponseEntity<>(new CustomError("Invalid Id's"), HttpStatus.BAD_REQUEST);
		} else if( !(FTE instanceof Double) ) {
			return new ResponseEntity<>(new CustomError("Invalid Body"), HttpStatus.BAD_REQUEST);
		} else if(FTE > 1 || FTE < 0) {
			return new ResponseEntity<>(new CustomError("Invalid Body"), HttpStatus.BAD_REQUEST);
		} else {
			if(granularity.equals("weekly")) {
				savedWeeklyFTE = weeklyFTEService.saveWeeklyFTE(new WeeklyFTE(resourceSpecificationId, resourceScheduleWeekNumber, FTE));
				if(savedWeeklyFTE==null) {
					return new ResponseEntity<>(new CustomError("not saved FTE in week " + resourceScheduleWeekNumber), HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>(savedWeeklyFTE, HttpStatus.OK);
				}
			}
			else if(granularity.equals("monthly")) {
				List<Long> weeks = getWeeksInMonth(resourceScheduleWeekNumber, WEEKSINMONTH);
				for(Long week : weeks) {
					savedWeeklyFTE = weeklyFTEService.saveWeeklyFTE(new WeeklyFTE(resourceSpecificationId, week, FTE));
					if(savedWeeklyFTE==null)  {
						return new ResponseEntity<>(new CustomError("not saved FTE in week number " + week), HttpStatus.BAD_REQUEST);
					}
				}
				return new ResponseEntity<>(savedWeeklyFTE, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new CustomError("Invalid url"), HttpStatus.BAD_REQUEST);
			}
		}
    }
	
	private List<Long> getWeeksInMonth(long monthNumber, long multiplier) {
		long min;
		long max;
		if(monthNumber == 1) {
			min = 0;
			max = 1;
		} else {
			min = ((monthNumber - 1) * multiplier) + 1;
			max = monthNumber * multiplier;
		}
		List<Long> weekNumbers = new ArrayList<Long>();
		for(long i = min; i <= max; i ++) {
			weekNumbers.add(i);
		}
		return weekNumbers;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/resourcespecifications/{resourceSpecificationId}/weeklyftes/{granularity}/{resourceScheduleWeekNumber}")
    public ResponseEntity<Object> updateWeeklyFTE(@PathVariable Long resourceSpecificationId, @PathVariable String granularity, @PathVariable Long resourceScheduleWeekNumber, 
    		@RequestBody(required=true) Double FTE) {
		WeeklyFTE savedWeeklyFTE = null;
		if( !(resourceSpecificationId instanceof Long) || !(resourceScheduleWeekNumber instanceof Long) ) {
			return new ResponseEntity<>(new CustomError("Invalid Id's"), HttpStatus.BAD_REQUEST);
		} else if(resourceSpecificationId <= 0 || resourceScheduleWeekNumber <= 0) {
			return new ResponseEntity<>(new CustomError("Invalid Id's"), HttpStatus.BAD_REQUEST);
		} else if( !(FTE instanceof Double) ) {
			return new ResponseEntity<>(new CustomError("Invalid Body"), HttpStatus.BAD_REQUEST);
		} else if(FTE > 1 || FTE < 0) {
			return new ResponseEntity<>(new CustomError("Invalid Body"), HttpStatus.BAD_REQUEST);
		} else {
			if(granularity.equals("weekly")) {
				savedWeeklyFTE = weeklyFTEService.saveWeeklyFTE(new WeeklyFTE(resourceSpecificationId, resourceScheduleWeekNumber, FTE));
				if(savedWeeklyFTE==null) {
					return new ResponseEntity<>(new CustomError("not saved FTE in week " + resourceScheduleWeekNumber), HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>(savedWeeklyFTE, HttpStatus.OK);
				}
			}
			else if(granularity.equals("monthly")) {
				List<Long> weeks = getWeeksInMonth(resourceScheduleWeekNumber, WEEKSINMONTH);
				for(Long week : weeks) {
					savedWeeklyFTE = weeklyFTEService.saveWeeklyFTE(new WeeklyFTE(resourceSpecificationId, week, FTE));
					if(savedWeeklyFTE==null)  {
						return new ResponseEntity<>(new CustomError("not saved FTE in week number " + week), HttpStatus.BAD_REQUEST);
					}
				}
				return new ResponseEntity<>(savedWeeklyFTE, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new CustomError("Invalid url"), HttpStatus.BAD_REQUEST);
			}
		}
    }
	
	@RequestMapping(method=RequestMethod.DELETE, value= "/resourcespecifications/{resourceSpecificationId}/weeklyftes/{granularity}/{resourceScheduleWeekNumber}")
    public ResponseEntity<Object> deleteWeeklyFTE(@PathVariable Long resourceSpecificationId, @PathVariable String granularity, @PathVariable Long resourceScheduleWeekNumber) {
		int deleteCount = 0;
		if( !(resourceSpecificationId instanceof Long) || !(resourceScheduleWeekNumber instanceof Long) ) {
			return new ResponseEntity<>(new CustomError("Invalid Id's"), HttpStatus.BAD_REQUEST);
		} else if(resourceSpecificationId <= 0 || resourceScheduleWeekNumber <= 0) {
			return new ResponseEntity<>(new CustomError("Invalid Id's"), HttpStatus.BAD_REQUEST);
		} else {
			if(granularity.equals("weekly")) {
				WeeklyFTEKey key = new WeeklyFTEKey(resourceSpecificationId, resourceScheduleWeekNumber);
				deleteCount = weeklyFTEService.deleteWeeklyFTE(key);
			}
			else if(granularity.equals("monthly")) {
				List<Long> weeks = getWeeksInMonth(resourceScheduleWeekNumber, WEEKSINMONTH);
				WeeklyFTEKey key;
				for(Long week : weeks) {
					key = new WeeklyFTEKey(resourceSpecificationId, week);
					deleteCount = deleteCount + weeklyFTEService.deleteWeeklyFTE(key);
				}
			} else {
				return new ResponseEntity<>(new CustomError("Invalid url"), HttpStatus.BAD_REQUEST);
			}
		}
		
		if(deleteCount == 1 || deleteCount == WEEKSINMONTH.intValue()) {
			return new ResponseEntity<>(deleteCount, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(deleteCount, HttpStatus.BAD_REQUEST);
		}
    }
	
	
}
