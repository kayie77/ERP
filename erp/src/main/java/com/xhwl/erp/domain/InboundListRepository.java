package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xhwl.erp.entity.InboundList;
import com.xhwl.erp.entity.PaymentContract;

public interface InboundListRepository extends JpaRepository<InboundList, Long>, JpaSpecificationExecutor<InboundList> {
	
	InboundList findByPaymentContract(PaymentContract paymentContract);
	
	@Query(" from InboundList il where il.paymentContract.contractInfo.business.region.id=?1")
	Page<InboundList> findAllByRegion(Long region_id,Pageable pageable);
	
	@Query("select i.id ,i.code ,i.paymentContract.contractInfo.name,"
			+ "i.paymentContract.contractInfo.business.region.name"
			+ " from InboundList i where i.code like %:code%")
	List<Object[]> getCodeArrary(@Param("code") String code);
	
	@Query("select i.id ,i.code ,i.paymentContract.contractInfo.name,"
			+ "i.paymentContract.contractInfo.business.region.name "
			+ "from InboundList i where i.code like %:code% "
			+ "and i.paymentContract.contractInfo.business.region.code = :region_code")
	List<Object[]> getCodeArrary(@Param("code") String code,@Param("region_code") String region_code);
	
}