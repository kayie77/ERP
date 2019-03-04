package com.xhwl.erp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.entity.ContractSchedule;

public interface ContractScheduleRepository extends JpaRepository<ContractSchedule, Long>, JpaSpecificationExecutor<ContractSchedule> {
	
	ContractSchedule findByContractInfo(ContractInfo ContractInfo);

}