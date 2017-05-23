package com.pointwest.workforce.planner.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pointwest.workforce.planner.domain.CustomError;
import com.pointwest.workforce.planner.domain.Opportunity;
import com.pointwest.workforce.planner.domain.OpportunityActivity;
import com.pointwest.workforce.planner.domain.ResourceSpecification;
import com.pointwest.workforce.planner.domain.Version;
import com.pointwest.workforce.planner.domain.WeeklyFTE;
import com.pointwest.workforce.planner.domain.WeeklyFTEKey;
import com.pointwest.workforce.planner.service.OpportunityActivityService;
import com.pointwest.workforce.planner.service.OpportunityService;
import com.pointwest.workforce.planner.service.ReferenceDataService;
import com.pointwest.workforce.planner.service.ResourceSpecificationService;
import com.pointwest.workforce.planner.service.TemplateDataService;
import com.pointwest.workforce.planner.service.VersionService;
import com.pointwest.workforce.planner.service.WeeklyFTEService;

@RestController
public class VersionController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@Autowired
	TemplateDataService templateDataService;

	@Autowired
	OpportunityService opportunityService;
	
	@Autowired
	OpportunityActivityService opportunityActivityService;
	
	@Autowired
	ResourceSpecificationService resourceSpecificationService;
	
	@Autowired
	WeeklyFTEService weeklyFTEService;
	
	@Autowired
	VersionService versionService;
	
	@Autowired
	JsonParser parser;
	
	private static final Logger log = LoggerFactory.getLogger(VersionController.class);
	
	@RequestMapping(method=RequestMethod.GET, value="opportunities/{opportunityId}/versions")
	public ResponseEntity<Object> fetchOpportunityVersions(@PathVariable Long opportunityId) {
		
        List<Version> versions = versionService.fetchVersions(opportunityId);
       
		if( versions == null || versions.isEmpty()) {
			return new ResponseEntity<>(new CustomError("Versions not found for supplied opportunity id"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(versions, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="opportunities/{opportunityId}/versions/{versionId}")
    public ResponseEntity<Object> fetchVersion(@PathVariable Long versionId) {
		ObjectMapper mapper = new ObjectMapper();

        Version version = versionService.fetchOpportunityVersion(versionId);
        Opportunity opportunity = null;
		try {
			opportunity = mapper.readValue(version.getVersionData(), Opportunity.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(version == null) {
			return new ResponseEntity<>(new CustomError("Version not found"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(opportunity, HttpStatus.OK);
		}
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/opportunities/{opportunityId}/versions")
    public ResponseEntity<Object> saveVersion(@RequestBody(required=true) String versionName, @PathVariable Long opportunityId) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonData;
		Opportunity opportunity = opportunityService.fetchOpportunity(opportunityId);		
		if(opportunity == null) {
			return new ResponseEntity<>(new CustomError("Opportunity not found"), HttpStatus.NOT_FOUND);
		} else {
			try {
				jsonData = mapper.writeValueAsString(opportunity);
				Version version = new Version();
				version.setOpportunityId(opportunityId);
				version.setVersionName(versionName);
				version.setVersionData(jsonData);
				versionService.saveVersion(version);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return new ResponseEntity<>(new CustomError("Error in saving version"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(opportunity, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="opportunities/{opportunityId}/versions/{versionId}/revert")
    public ResponseEntity<Object> revertVersion(@PathVariable Long opportunityId, @PathVariable Long versionId) {
		ObjectMapper mapper = new ObjectMapper();
        Version version = versionService.fetchOpportunityVersion(versionId);
        Opportunity currentOpportunity = null;
        Opportunity versionedOpportunity = null;
		try {
			versionedOpportunity = mapper.readValue(version.getVersionData(), Opportunity.class);
			currentOpportunity = opportunityService.fetchOpportunity(opportunityId);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long opportunityActivityId;
		for (OpportunityActivity opportunityActivity : currentOpportunity.getOpportunityActivities()) {
			opportunityActivityId = opportunityActivity.getOpportunityActivityId();
			int result = opportunityActivityService.deleteOpportunityActivity(opportunityActivityId);
			log.debug("delete id " + opportunityActivityId + " result : " + result);
		}
		//bmab check for refactoring
		versionedOpportunity.setOpportunityCollaborators(currentOpportunity.getOpportunityCollaborators());
		OpportunityActivity transAct;
		ResourceSpecification transRes;
		List<ResourceSpecification> ress;
		List<WeeklyFTE> weeks;
		WeeklyFTEKey key = null;
		for(OpportunityActivity act : versionedOpportunity.getOpportunityActivities()) {
			act.setOpportunityId(versionedOpportunity.getOpportunityId());
			log.debug("act opp id " + versionedOpportunity.getOpportunityId());
			ress = act.getResourceSpecificationList();
			act.setResourceSpecificationList(null);
			transAct = opportunityActivityService.saveOpportunityActivity(act);
			log.debug("res oppact id " + transAct.getOpportunityActivityId());
			for(ResourceSpecification res : ress) {
				res.setResourceSpecificationId(null);
				res.setOpportunityActivityId(transAct.getOpportunityActivityId());
				weeks = res.getResourceSchedule();
				res.setResourceSchedule(null);
				transRes = resourceSpecificationService.saveResourceSpecification(res);
				log.debug("week resspec id " + transRes.getResourceSpecificationId());
				for(WeeklyFTE week : weeks) {
					key = week.getKey();
					key.setResourceSpecificationId(transRes.getResourceSpecificationId());
					week.setKey(key);
					weeklyFTEService.saveWeeklyFTE(week);
				}
			}
		}
		versionedOpportunity.setOpportunityActivities(null);
		versionedOpportunity = opportunityService.saveOpportunity(versionedOpportunity);
		
		if (versionedOpportunity == null) {
			currentOpportunity = opportunityService.saveOpportunity(currentOpportunity);
			return new ResponseEntity<>(new CustomError("Error version not applied"), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(versionedOpportunity, HttpStatus.OK);
		}
	}
	
		/*Version savedVersion = null;
		boolean isNew = false;
		if(version==null) {
			savedVersion = versionService.saveVersion(new Version());
			isNew = true;
		} else if(version.getVersionId() == null) {
			savedVersion = versionService.saveVersion(version);
			isNew = true;
		} else {
			savedVersion = versionService.saveVersion(version);
			isNew = false;
		}
		if(savedVersion==null) {
			return new ResponseEntity<>(new CustomError("Invalid inputs, not saved"), HttpStatus.BAD_REQUEST);
		} else {
			if(isNew) {
				return new ResponseEntity<>(savedVersion, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(savedVersion, HttpStatus.OK);
			}
		}*/
    
}
