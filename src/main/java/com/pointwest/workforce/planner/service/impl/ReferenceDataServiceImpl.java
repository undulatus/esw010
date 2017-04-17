package com.pointwest.workforce.planner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.data.ActivityRepository;
import com.pointwest.workforce.planner.data.GroupRepository;
import com.pointwest.workforce.planner.data.MarketCircleRepository;
import com.pointwest.workforce.planner.data.PayLevelRepository;
import com.pointwest.workforce.planner.data.PracticeRepository;
import com.pointwest.workforce.planner.data.RoleRepository;
import com.pointwest.workforce.planner.data.ServiceLineRepository;
import com.pointwest.workforce.planner.domain.Activity;
import com.pointwest.workforce.planner.domain.Group;
import com.pointwest.workforce.planner.domain.MarketCircle;
import com.pointwest.workforce.planner.domain.PayLevel;
import com.pointwest.workforce.planner.domain.Practice;
import com.pointwest.workforce.planner.domain.Role;
import com.pointwest.workforce.planner.domain.ServiceLine;
import com.pointwest.workforce.planner.service.ReferenceDataService;

@Service
public class ReferenceDataServiceImpl implements ReferenceDataService {

		@Autowired
		public MarketCircleRepository marketCircleRepository;
		
		@Autowired
		public ServiceLineRepository serviceLineRepository;
		
		@Autowired
		public ActivityRepository activityRepository;
		
		@Autowired
		public GroupRepository groupRepository;
		
		@Autowired
		public PayLevelRepository paylevelRepository;
		
		@Autowired
		public PracticeRepository practiceRepository;
		
		@Autowired
		public RoleRepository roleRepository;
		
		private static final Logger log = LoggerFactory.getLogger(ReferenceDataServiceImpl.class);
		
		@Override
		public List<MarketCircle> fetchAllMarketCircle() {
//			List<MarketCircle> marketCircles = new ArrayList<MarketCircle>(); 
//			marketCircleRepository.findAll().forEach(marketCircles::add);
//			return marketCircles;
			log.debug("MCI >> fetchAllMarketCircle");
			List<MarketCircle> marketCircles = (List<MarketCircle>) marketCircleRepository.findAll();
			log.debug("MCO >> fetchAllMarketCircle");
			return marketCircles;
		}
		
		@Override
		public MarketCircle fetchMarketCircle(int marketCircleId) {
			log.debug("MCI >> fetchMarketCircle with id : " + marketCircleId);
			MarketCircle marketCircle = marketCircleRepository.findOne(marketCircleId);
			log.debug("MCO >> fetchMarketCircle with id : " + marketCircleId);
			return marketCircle;
		}
		
		@Override
		public int addMarketCircle(MarketCircle marketCircle) {
			log.debug("MCI >> addMarketCircle");
			MarketCircle saved = marketCircleRepository.save(marketCircle);
			int result = saved != null ?  1 : 0;
			log.debug("MCO >> addMarketCircle result " + result);
			return result;
		}
		
		@Override
		public int updateMarketCircle(int marketCircleId, MarketCircle marketCircle) {
//			MarketCircle saved = marketCircleRepository.save(marketCircleId, marketCircle);
//			return saved != null ?  1 : 0;
			return 0;
		}

		@Override
		public List<ServiceLine> fetchAllServiceLine() {
			log.debug("MCI >> fetchAllServiceLine");
			List<ServiceLine> serviceLines = (List<ServiceLine>) serviceLineRepository.findAll();
			log.debug("MCO >> fetchAllServiceLine");
			return serviceLines;
			
		}

		@Override
		public ServiceLine fetchServiceLine(int serviceLineId) {
			log.debug("MCI >> fetchServiceLine with id: " + serviceLineId);
			ServiceLine serviceLine = serviceLineRepository.findOne(serviceLineId);
			log.debug("MCI >> fetchServiceLine with id: " + serviceLineId);
			return serviceLine;
		}

		@Override
		public int addServiceLine(ServiceLine serviceLine) {
			log.debug("MCI >> addServiceLine");
			ServiceLine saved = serviceLineRepository.save(serviceLine);
			int result = saved != null ?  1 : 0;
			log.debug("MCO >> addServiceLine result " + result);
			return result;
		}

		@Override
		public int updateServiceLine(int serviceLineId, ServiceLine serviceLine) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public List<Activity> fetchAllActivity() {
			log.debug("MCI >> fetchAllActivity");
			List<Activity> activities = (List<Activity>) activityRepository.findAll();
			log.debug("MCO >> fetchAllActivity");
			return activities;
		}

		@Override
		public Activity fetchActivity(int activityId) {
			log.debug("MCI >> fetchActivity with id: " + activityId);
			Activity activity = activityRepository.findOne(activityId);
			log.debug("MCO >> fetchActivity with id: " + activityId);
			return activity;
		}

		@Override
		public int addActivity(Activity activity) {
			log.debug("MCI >> addActivity");
			Activity saved = activityRepository.save(activity);
			int result = saved != null ?  1 : 0;
			log.debug("MCO >> addActivity result : " + result);
			return result;
		}

		@Override
		public List<Group> fetchAllGroup() {
			log.debug("MCI >> fetchAllGroup");
			List<Group> groups = (List<Group>) groupRepository.findAll();
			log.debug("MCO >> fetchAllGroup");
			return groups;
		}

		@Override
		public Group fetchGroup(int groupId) {
			log.debug("MCI >> fetchGroup with id: " + groupId);
			Group group = groupRepository.findOne(groupId);
			log.debug("MCO >> fetchGroup with id: " + groupId);
			return group;
		}

		@Override
		public List<PayLevel> fetchAllPayLevel() {
			log.debug("MCI >> fetchAllPayLevel");
			List<PayLevel> payLevels = (List<PayLevel>) paylevelRepository.findAll();
			log.debug("MCO >> fetchAllPayLevel");
			return payLevels;
		}

		@Override
		public PayLevel fetchPayLevel(int payLevelId) {
			log.debug("MCI >> fetchPayLevel with id: " + payLevelId);
			PayLevel payLevel = paylevelRepository.findOne(payLevelId);
			log.debug("MCO >> fetchPayLevel with id: " + payLevelId);
			return payLevel;
		}

		@Override
		public List<Practice> fetchAllPractice() {
			log.debug("MCI >> fetchAllPractice");
			List<Practice> practices = (List<Practice>) practiceRepository.findAll();
			log.debug("MCO >> fetchAllPractice");
			return practices;
		}

		@Override
		public Practice fetchPractice(int practiceId) {
			log.debug("MCI >> fetchPractice with id: " + practiceId);
			Practice practice = practiceRepository.findOne(practiceId);
			log.debug("MCO >> fetchPractice with id: " + practiceId);
			return practice;
		}

		@Override
		public List<Role> fetchAllRole() {
			log.debug("MCI >> fetchAllRole");
			List<Role> roles = (List<Role>) roleRepository.findAll();
			log.debug("MCO >> fetchAllRole");
			return roles;
		}

		@Override
		public Role fetchRole(int roleId) {
			log.debug("MCI >> fetchRole with id: " + roleId);
			Role role = roleRepository.findOne(roleId);
			log.debug("MCI >> fetchRole with id: " + roleId);
			return role;
		}
		
		
	}

