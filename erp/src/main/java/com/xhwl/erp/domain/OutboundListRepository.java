package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xhwl.erp.entity.OutboundList;

public interface OutboundListRepository extends JpaRepository<OutboundList, Long>, JpaSpecificationExecutor<OutboundList> {

	@Query(" from OutboundList ol where ol.inboundList.paymentContract.contractInfo.business.region.id=?1")
	Page<OutboundList> findAllByRegion(Long region_id,Pageable pageable);
	
	@Query("select i.id ,i.code from OutboundList i where i.code like %:code%")
	List<Object[]> getCodeArrary(@Param("code") String code);
	
	@Query("select i.id ,i.code from OutboundList i where i.code like %:code% "
			+ "and i.inboundList.paymentContract.contractInfo.business.region.code = :region_code")
	List<Object[]> getCodeArrary(@Param("code") String code,@Param("region_code") String region_code);
}