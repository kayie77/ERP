package com.xhwl.erp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.entity.ContractSubcontract;

public interface ContractSubcontractRepository extends JpaRepository<ContractSubcontract, Long>, JpaSpecificationExecutor<ContractSubcontract> {
	
	Page<ContractSubcontract> findByContractInfo(ContractInfo ContractInfo,Pageable pageable);
	
}