package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.OutboundDetaile;
import com.xhwl.erp.entity.OutboundList;
import com.xhwl.erp.entity.PurchaseList;

public interface OutboundDetaileRepository extends JpaRepository<OutboundDetaile, Long>, JpaSpecificationExecutor<OutboundDetaile> {

	Page<OutboundDetaile> findByOutboundList(OutboundList outboundList,Pageable pageable);
	
	List<OutboundDetaile> findByOutboundList(OutboundList outboundList);
	
	OutboundDetaile findByPurchaseList(PurchaseList purchaseList);
}