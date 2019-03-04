package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xhwl.erp.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client>  {
	
	Client findByName(String name);
	List<Client> findByCategory(String category);
	
	@Query("select c.id ,c.name,c.category,c.phone from Client c where c.name like %:name%")
	List<Object[]> getNameArrary(@Param("name") String name);
}