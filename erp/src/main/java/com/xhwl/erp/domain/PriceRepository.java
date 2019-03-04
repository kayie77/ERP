package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xhwl.erp.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Long>, JpaSpecificationExecutor<Price> {
	
	@Query("select p.code from Price p")
	String[] getCode();
	
	@Query("select p.id ,p.code,p.name from Price p where p.name like %:name%")
	List<Object[]> getNameArrary(@Param("name") String name);
	
	@Query("select p.id ,p.code,p.name from Price p where p.code like %:code%")
	List<Object[]> getCodeArrary(@Param("code") String code);
}