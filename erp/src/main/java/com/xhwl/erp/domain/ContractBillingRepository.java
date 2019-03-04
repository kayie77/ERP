package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xhwl.erp.entity.ContractBilling;
import com.xhwl.erp.entity.ContractInfo;

public interface ContractBillingRepository extends JpaRepository<ContractBilling, Long>, JpaSpecificationExecutor<ContractBilling> {
	
	Page<ContractBilling> findByContractInfo(ContractInfo ContractInfo,Pageable pageable);
	
	List<ContractBilling> findByContractInfo(ContractInfo ContractInfo);
	
	ContractBilling findByNumber(String number);
	
	@Query("select c.id ,c.number ,c.name from ContractBilling c where c.number like %:number%")
	List<Object[]> getNumberArrary(@Param("number") String number);
	
	@Query("select c.id ,c.number ,c.name from ContractBilling c where c.number like %:number% and c.contractInfo.business.region.code = :region_code")
	List<Object[]> getNumberArrary(@Param("number") String number,@Param("region_code") String region_code);
	
}