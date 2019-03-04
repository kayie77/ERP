package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xhwl.erp.entity.Business;
import com.xhwl.erp.entity.BusinessCategory;
import com.xhwl.erp.entity.Region;

public interface BusinessRepository extends JpaRepository<Business, Long>, JpaSpecificationExecutor<Business> {
	
	Business findByName(String name);
	
	Page<Business> findByRegion(Region region,Pageable pageable);
	
	List<Business> findByRegion(Region region);
	
	List<Business> findByBusinessCategory(BusinessCategory businessCategory);
	
	@Query("select b.code from Business b where b.code like %:date%")
	String[] getCodeStingList(@Param("date") String date);
	
	@Query("select b.id ,b.code ,b.name,b.client.name,b.amount,b.businessCategory.name,b.client.category,b.city.name,b.region.name from Business b where b.code like %:code%")
	List<Object[]> getCodeArrary(@Param("code") String code);
	
	@Query("select b.id ,b.code ,b.name,b.client.name,b.amount,b.businessCategory.name,b.client.category,b.city.name,b.region.name from Business b where b.code like %:code% and b.region.code = :region_code")
	List<Object[]> getCodeArrary(@Param("code") String code,@Param("region_code") String region_code);
	
	@Query("select b.id ,b.code ,b.name,b.client.name,b.amount,b.businessCategory.name,b.client.category,b.city.name,b.region.name from Business b where b.name like %:name% ")
	List<Object[]> getNameArrary(@Param("name") String name);
	
	@Query("select b.id ,b.code ,b.name,b.client.name,b.amount,b.businessCategory.name,b.client.category,b.city.name,b.region.name from Business b where b.name like %:name% and b.region.code = :region_code")
	List<Object[]> getNameArrary(@Param("name") String name,@Param("region_code") String region_code);

}