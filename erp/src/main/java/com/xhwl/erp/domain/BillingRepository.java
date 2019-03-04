package com.xhwl.erp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.Billing;
import com.xhwl.erp.entity.PaymentContract;

public interface BillingRepository extends JpaRepository<Billing, Long>, JpaSpecificationExecutor<Billing> {
	
	Page<Billing> findByPaymentContract(PaymentContract paymentContract,Pageable pageable);
	
}