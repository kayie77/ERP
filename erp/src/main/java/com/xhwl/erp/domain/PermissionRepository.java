package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.xhwl.erp.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {
	
	@Query(value = "	SELECT\n" + 
			"    a.id accountId,\n" + 
			"    a.name accountName,\n" + 
			"    a.role_id roleId,\n" + 
			"    r.name roleName,\n" + 
			"    r.code roleCode,\n" + 
			"    u.name userName,\n" + 
			"    u.code userCode,\n" + 
			"    p.menu_code menuCode,\n" + 
			"    p.permission_code permissionCode\n" + 
			"    FROM sys_account a\n" + 
			"    LEFT JOIN sys_role r ON r.id = a.role_id\n" + 
			"    LEFT JOIN sys_role_permission rp ON a.role_id = rp.role_id\n" + 
			"    LEFT JOIN sys_permission p ON rp.permission_id = p.id\n" + 
			"    LEFT JOIN sys_user u ON u.account_id = a.id\n" + 
			"    WHERE a.name = ?", nativeQuery = true)
	List<Object[]> getUserPermission(String username);
	
	/**
	 * 	    SELECT
            r.id              roleId,
            r.name       roleName,
            p.id              permissionId,
            p.menu_code       menuCode,
            p.menu_name       menuName,
            p.permission_name permissionName
            FROM sys_role r
            LEFT JOIN sys_role_permission rp ON r.id = rp.role_id 
            LEFT JOIN sys_permission p ON rp.permission_id = p.id
       		ORDER BY r.id, p.id
	 * @author jiayiwu
	 * @date 2018年4月17日
	 * @return
	 */
	
	@Query(value = "SELECT\n" + 
			"    r.id         roleId,\n" + 
			"    r.name       roleName,\n" + 
			"    p.id         permissionId,\n" + 
			"    p.menu_code  menuCode,\n" + 
			"    p.menu_name  menuName,\n" + 
			"    p.permission_name permissionName\n" + 
			"    FROM sys_role r\n" + 
			"    LEFT JOIN sys_role_permission rp ON r.id = rp.role_id\n" + 
			"    LEFT JOIN sys_permission p ON rp.permission_id = p.id\n" + 
			"    ORDER BY r.id, p.id limit ? offset ?", nativeQuery = true)
	List<Object[]> listRolePermission(int size, int page);
	
	@Query(value = "SELECT count(1) count " + 
			"    FROM sys_role r\n" + 
			"    LEFT JOIN sys_role_permission rp ON r.id = rp.role_id\n" + 
			"    LEFT JOIN sys_permission p ON rp.permission_id = p.id\n" + 
			"    ORDER BY r.id, p.id", nativeQuery = true)
	int rolePermissionTotalCount();
	
	@Query(value = "	SELECT p.id id,\n" + 
			"	 p.menu_name menuName,\n" + 
			"	 p.permission_name permissionName\n" + 
			"    FROM sys_permission p\n" + 
			"    ORDER BY p.id", nativeQuery = true)
	List<Object[]> findAllPermission();
	
	@Query(value = "	SELECT p.permission_code permissionCode\n" + 
			"    FROM sys_permission p\n" + 
			"    ORDER BY p.id", nativeQuery = true)
	List<String> getAllPermission();

	@Query(value = "SELECT p.menu_code menuCode\n" + 
			"    FROM sys_permission p\n" + 
			"    ORDER BY p.id", nativeQuery = true)
	List<String> getAllMenu();
	
}