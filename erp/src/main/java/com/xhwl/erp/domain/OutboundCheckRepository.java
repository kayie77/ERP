package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.OutboundCheck;
import com.xhwl.erp.entity.OutboundList;

public interface OutboundCheckRepository extends JpaRepository<OutboundCheck, Long>, JpaSpecificationExecutor<OutboundCheck> {

	List<OutboundCheck> findByOutboundList(OutboundList outboundList);
}