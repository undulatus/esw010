package com.pointwest.workforce.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.MarketCircle;
import com.pointwest.workforce.planner.service.ReferenceDataService;

@RestController
public class MarketCircleController {
	
	@Autowired
	ReferenceDataService referenceDataService;
	
	@RequestMapping("/marketcircles")
    public List<MarketCircle> fetchAllMarketCircle() {
       return referenceDataService.fetchAllMarketCircle();
    }
	
	@RequestMapping("/marketcircles/{marketCircleId}")
    public MarketCircle fetchMarketCircle(@PathVariable int marketCircleId) {
       return referenceDataService.fetchMarketCircle(marketCircleId);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/marketcircles")
    public void addMarketCircle(@RequestBody MarketCircle marketCircle) {
		referenceDataService.addMarketCircle(marketCircle);
    }
	
	@RequestMapping(method=RequestMethod.PUT, value="/marketcircles/{marketCircleId}")
    public void updateMarketCircle(@RequestBody MarketCircle marketCircle, Integer marketCircleId) {
		referenceDataService.updateMarketCircle(marketCircleId, marketCircle);
    }

}
