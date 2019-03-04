package com.xhwl.erp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.Payment;
import com.xhwl.erp.entity.PaymentContract;

public interface PaymentRepository extends JpaRepository<Payment, Long>, JpaSpecificationExecutor<Payment> {
	
	Page<Payment> findByPaymentContract(PaymentContract paymentContract,Pageable pageable);

}