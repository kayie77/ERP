package com.xhwl.erp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.ContractChange;
import com.xhwl.erp.entity.ContractInfo;

public interface ContractChangeRepository extends JpaRepository<ContractChange, Long>, JpaSpecificationExecutor<ContractChange> {
	
	Page<ContractChange> findByContractInfo(ContractInfo ContractInfo,Pageable pageable);
	
}