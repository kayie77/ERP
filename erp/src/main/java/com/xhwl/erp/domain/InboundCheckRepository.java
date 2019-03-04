package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhwl.erp.entity.InboundCheck;
import com.xhwl.erp.entity.InboundList;

public interface InboundCheckRepository extends JpaRepository<InboundCheck, Long>, JpaSpecificationExecutor<InboundCheck> {

	List<InboundCheck> findByInboundList(InboundList inboundList);
}