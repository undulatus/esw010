package com.pointwest.workforce.planner.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pointwest.workforce.planner.domain.MarketCircle;

public interface MarketCircleRepository extends CrudRepository<MarketCircle, Integer> {
	public List<MarketCircle> findAll();
}