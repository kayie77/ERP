package com.xhwl.erp.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.xhwl.erp.entity.Region;

public interface RegionRepository extends PagingAndSortingRepository<Region, Long> {
	
	Region findByCode(String code);

}