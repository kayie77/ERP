package com.xhwl.erp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.ContractAnalysis;
import com.xhwl.erp.entity.ContractInfo;

public interface ContractAnalysisRepository extends JpaRepository<ContractAnalysis, Long>, JpaSpecificationExecutor<ContractAnalysis> {
	
	ContractAnalysis findByContractInfo(ContractInfo ContractInfo);

}