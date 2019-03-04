package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.xhwl.erp.entity.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long>, JpaSpecificationExecutor<RolePermission> {

	@Query("	select rp from RolePermission rp where rp.role_id = ?1")
	List<RolePermission> getRolePermission(Long role_id);
	
}