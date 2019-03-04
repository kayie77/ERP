package com.xhwl.erp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.ContractBasis;
import com.xhwl.erp.entity.ContractInfo;

public interface ContractBasisRepository extends JpaRepository<ContractBasis, Long>, JpaSpecificationExecutor<ContractBasis> {
	
	Page<ContractBasis> findByContractInfo(ContractInfo ContractInfo,Pageable pageable);
	
}