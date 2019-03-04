package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.PaymentContract;
import com.xhwl.erp.entity.PurchaseList;

public interface PurchaseListRepository extends JpaRepository<PurchaseList, Long>, JpaSpecificationExecutor<PurchaseList> {
	
	Page<PurchaseList> findByPaymentContract(PaymentContract paymentContract,Pageable pageable);
	
	List<PurchaseList> findByPaymentContract(PaymentContract paymentContract);

}