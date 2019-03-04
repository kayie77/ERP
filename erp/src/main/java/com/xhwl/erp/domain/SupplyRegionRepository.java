package com.xhwl.erp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.SupplyRegion;

public interface SupplyRegionRepository extends JpaRepository<SupplyRegion, Long>, JpaSpecificationExecutor<SupplyRegion> {
	

}