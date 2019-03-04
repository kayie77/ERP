package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.entity.ContractPayment;

public interface ContractPaymentRepository extends JpaRepository<ContractPayment, Long>, JpaSpecificationExecutor<ContractPayment> {
	
	Page<ContractPayment> findByContractInfo(ContractInfo ContractInfo,Pageable pageable);
	List<ContractPayment> findByContractInfo(ContractInfo ContractInfo);

}