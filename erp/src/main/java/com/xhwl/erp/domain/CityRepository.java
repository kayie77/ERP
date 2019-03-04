package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.xhwl.erp.entity.City;

public interface CityRepository  extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
	
	@Query("select c from City c")
	public List<City> findCityByList();

}