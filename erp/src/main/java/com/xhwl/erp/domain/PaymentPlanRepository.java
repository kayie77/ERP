package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.xhwl.erp.entity.PaymentPlan;
import com.xhwl.erp.entity.ContractBasis;

public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, Long>, JpaSpecificationExecutor<PaymentPlan> {
	
	Page<PaymentPlan> findByContractBasis(ContractBasis contractBasis,Pageable pageable);
	
	@Query("select p from PaymentPlan p")
	List<PaymentPlan> getAll();

}