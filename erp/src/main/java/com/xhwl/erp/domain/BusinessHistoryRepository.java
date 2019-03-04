package com.xhwl.erp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.Business;
import com.xhwl.erp.entity.BusinessHistory;

public interface BusinessHistoryRepository extends JpaRepository<BusinessHistory, Long>, JpaSpecificationExecutor<BusinessHistory> {
	
	Page<BusinessHistory> findByBusiness(Business business,Pageable pageable);
}