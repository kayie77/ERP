package com.xhwl.erp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.Price;
import com.xhwl.erp.entity.PriceHistory;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long>, JpaSpecificationExecutor<PriceHistory> {
	
	Page<PriceHistory> findByPrice(Price price,Pageable pageable);
}