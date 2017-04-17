package com.pointwest.workforce.planner.service.impl;

import org.springframework.stereotype.Service;

import com.pointwest.workforce.planner.domain.Opportunity;
import com.pointwest.workforce.planner.domain.Version;
import com.pointwest.workforce.planner.service.VersionService;

@Service
public class VersionServiceImpl implements VersionService {

	@Override
	public int saveVersion(Version version) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Opportunity fetchAndLoadOpportunityVersion(long versionId, long opportunityId) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	@Autowired
	public OpportunityRepository opportunityRepository;
	
	@Autowired
	public OpportunityLockEntityRepository opportunityEntityRepository;
	
	@Autowired
	public ReferenceDataService referenceDataService;
	
	@Value("${opportunity.documentstatus.locked}")
	private String LOCKED;
	
	@Value("${opportunity.documentstatus.unlocked}")
	private String UNLOCKED;
	
	private static final Logger log = LoggerFactory.getLogger(WorkforcePlannerApplication.class);
	
	@Override
	public List<Opportunity> fetchAllOpportunities() {
		List<Opportunity> opportunities = new ArrayList<Opportunity>(); 
		opportunityRepository.findAll().forEach(opportunities::add);
//		
//		for(Opportunity opportunity : opportunities) {
//			log.debug(opportunity.getOpportunityName() + " MC: " + opportunity.getMarketCircle().getMarketCircleId());
//		}
		return opportunities;
	}
	
	@Override
	public Opportunity fetchOpportunity(long opportunityId) {
		return opportunityRepository.findOne(opportunityId);
	}
	
	@Override
	public int saveOpportunity(Opportunity opportunity) {
		Opportunity saved = opportunityRepository.save(opportunity);
		return saved != null ?  1 : 0;
	}
	
	@Override
	public int updateOpportunity(Opportunity opportunity) {
		Opportunity saved = opportunityRepository.save(opportunity);
		
		return saved != null ?  1 : 0;
	}

	@Override
	public List<Opportunity> fetchOpportunityList() {
		List<Opportunity> opportunityList = opportunityRepository.findOpportunityList();
		return opportunityList;
	}

	//@Override
	public boolean lockOpportunityPre(long opportunityId, boolean lock) {
		String documentStatus = (lock == true ? LOCKED : UNLOCKED);
		opportunityRepository.updateOpportunityDocumentStatus(opportunityId, documentStatus);
		return true;
	}
	
	@Override
	public int lockOpportunity(long opportunityId, boolean lock) {
		String documentStatus = (lock == true ? LOCKED : UNLOCKED);
		OpportunityLockEntity opportunity = new OpportunityLockEntity(opportunityId, documentStatus);
		OpportunityLockEntity saved = opportunityEntityRepository.save(opportunity);
		return saved != null ?  1 : 0;
	}

	@Override
	public List<Opportunity> fetchOpportunitiesByUsername(String username) {
		return opportunityRepository.findByUserUsername(username);
	}
*/
}
