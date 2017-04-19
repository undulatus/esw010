package com.pointwest.workforce.planner.service;

import java.util.List;

import com.pointwest.workforce.planner.domain.Activity;
import com.pointwest.workforce.planner.domain.Group;
import com.pointwest.workforce.planner.domain.BusinessUnit;
import com.pointwest.workforce.planner.domain.PayLevel;
import com.pointwest.workforce.planner.domain.Practice;
import com.pointwest.workforce.planner.domain.Role;
import com.pointwest.workforce.planner.domain.ServiceType;

public interface ReferenceDataService {

	public List<BusinessUnit> fetchAllBusinessUnit();

	public BusinessUnit fetchBusinessUnit(int businessUnitId);

	public int addBusinessUnit(BusinessUnit businessUnit);

	public int updateBusinessUnit(int businessUnitId, BusinessUnit businessUnit);
	
	public List<ServiceType> fetchAllServiceType();

	public ServiceType fetchServiceType(int serviceTypeId);

	public int addServiceType(ServiceType serviceType);

	public int updateServiceType(int serviceTypeId, ServiceType serviceType);
	
	public List<Activity> fetchAllActivity();

	public Activity fetchActivity(int activityId);
	
	public Activity addActivity(Activity activity);
	
	public List<Group> fetchAllGroup();

	public Group fetchGroup(int groupId);
	
	public List<PayLevel> fetchAllPayLevel();

	public PayLevel fetchPayLevel(int payLevelId);
	
	public List<Practice> fetchAllPractice();

	public Practice fetchPractice(int practiceId);
	
	public List<Role> fetchAllRole();

	public Role fetchRole(int roleId);
	

}
