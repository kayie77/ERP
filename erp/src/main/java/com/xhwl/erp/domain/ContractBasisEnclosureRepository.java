package com.xhwl.erp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.ContractBasis;
import com.xhwl.erp.entity.ContractBasisEnclosure;

public interface ContractBasisEnclosureRepository extends JpaRepository<ContractBasisEnclosure, Long>, JpaSpecificationExecutor<ContractBasisEnclosure> {
	
	Page<ContractBasisEnclosure> findByContractBasis(ContractBasis contractBasis,Pageable pageable);
	
}