package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xhwl.erp.entity.Supply;

public interface SupplyRepository extends JpaRepository<Supply, Long>, JpaSpecificationExecutor<Supply> {

	@Query("select s.id ,s.name,s.type,s.supplyRegion.name,s.supplyCycle from Supply s where s.name like %:name%")
	List<Object[]> getNameArrary(@Param("name") String name);
}