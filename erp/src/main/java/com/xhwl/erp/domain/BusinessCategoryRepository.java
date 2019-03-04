package com.xhwl.erp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.BusinessCategory;

public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Long>, JpaSpecificationExecutor<BusinessCategory> {
	
}