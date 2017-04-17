package com.pointwest.workforce.planner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.data.ActivityRepository;
import com.pointwest.workforce.planner.data.PracticeRepository;
import com.pointwest.workforce.planner.data.RoleRepository;
import com.pointwest.workforce.planner.domain.Activity;
import com.pointwest.workforce.planner.domain.Practice;
import com.pointwest.workforce.planner.domain.Role;
import com.pointwest.workforce.planner.service.TemplateDataService;

@Service
public class TemplateDataServiceImpl implements TemplateDataService {

		@Autowired
		public ActivityRepository activityRepository;
		
		@Autowired
		public PracticeRepository practiceRepository;
		
		@Autowired
		public RoleRepository roleRepository;
		
		private static final Logger log = LoggerFactory.getLogger(TemplateDataServiceImpl.class);

		@Override
		public List<Activity> fetchActivitiesByServiceLineId(int serviceLineId) {
			log.debug("MCI >> fetchActivitiesByServiceLineId with serviceLineId: " + serviceLineId);
			List<Activity> activities = (List<Activity>) activityRepository.findActivitiesByServiceLineId(serviceLineId);
			log.debug("MCO >> fetchActivitiesByServiceLineId with serviceLineId: " + serviceLineId);
			return activities;
		}

		@Override
		public List<Role> fetchRolesByServiceLineId(int serviceLineId) {
			log.debug("MCI >> fetchRolesByServiceLineId with serviceLineId: " + serviceLineId);
			List<Role> roles = (List<Role>) roleRepository.findRolesByServiceLineId(serviceLineId);
			log.debug("MCO >> fetchRolesByServiceLineId with serviceLineId: " + serviceLineId);
			return roles;
		}

		@Override
		public List<Practice> fetchPracticesByRoleId(int roleId) {
			log.debug("MCI >> fetchPracticesByRoleId with roleId: " + roleId);
			List<Practice> practices = (List<Practice>) practiceRepository.findPracticesByRoleId(roleId);
			log.debug("MCO >> fetchPracticesByRoleId with roleId: " + roleId);
			return practices;
		}
		
		
}

