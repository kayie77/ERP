package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xhwl.erp.entity.Business;
import com.xhwl.erp.entity.ContractInfo;

public interface ContractInfoRepository extends JpaRepository<ContractInfo, Long>, JpaSpecificationExecutor<ContractInfo> {
	
	ContractInfo findByBusiness(Business business);
	
	ContractInfo findByCode(String code);
	
//	@Query(" from ContractInfo ci where ci.business.region.id=?1")
//	Page<ContractInfo> findAllByRegion(Long region_id,Pageable pageable);
	
	@Query("select c.id ,c.code ,c.name,c.business.businessCategory.name  from ContractInfo c where c.code like %:code%")
	List<Object[]> getCodeArrary(@Param("code") String code);
	
	@Query("select c.id ,c.code ,c.name,c.business.businessCategory.name  from ContractInfo c where c.code like %:code% and c.business.region.code = :region_code")
	List<Object[]> getCodeArrary(@Param("code") String code,@Param("region_code") String region_code);
	
	@Query("select c.id ,c.code ,c.name,c.business.businessCategory.name  from ContractInfo c where c.name like %:name% ")
	List<Object[]> getNameArrary(@Param("name") String name);
	
	@Query("select c.id ,c.code ,c.name,c.business.businessCategory.name  from ContractInfo c where c.name like %:name% and c.business.region.code = :region_code")
	List<Object[]> getNameArrary(@Param("name") String name,@Param("region_code") String region_code);

}