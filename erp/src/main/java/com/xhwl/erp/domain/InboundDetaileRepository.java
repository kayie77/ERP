package com.xhwl.erp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.InboundDetaile;
import com.xhwl.erp.entity.PurchaseList;

public interface InboundDetaileRepository extends JpaRepository<InboundDetaile, Long>, JpaSpecificationExecutor<InboundDetaile> {
	
	InboundDetaile findByPurchaseList(PurchaseList purchaseList);

}