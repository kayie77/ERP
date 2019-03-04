package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.ContractBilling;
import com.xhwl.erp.entity.ContractReceived;

public interface ContractReceivedRepository extends JpaRepository<ContractReceived, Long>, JpaSpecificationExecutor<ContractReceived> {
	
	Page<ContractReceived> findByContractBilling(ContractBilling contractBilling,Pageable pageable);
	
	List<ContractReceived> findByContractBilling(ContractBilling contractBilling);

}