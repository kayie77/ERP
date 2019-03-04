package com.xhwl.erp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.xhwl.erp.entity.TenderOffer;

public interface TenderOfferRepository extends JpaRepository<TenderOffer, Long>, JpaSpecificationExecutor<TenderOffer> {

	@Query(" from TenderOffer to where to.business.region.id=?1")
	Page<TenderOffer> findAllByRegion(Long region_id,Pageable pageable);
}