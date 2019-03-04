package com.xhwl.erp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.service.PermissionService;
import com.xhwl.erp.util.result.ResultJson;

@RestController
@RequestMapping(path="/permission")
@Api(description="权限管理(基础)")
public class PermissionController {
	
	@Autowired
    private PermissionService permissionService ;
	
	/**
	 * 获取全部权限
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAll")
	@RequiresPermissions("permission:findAll")
	@ApiOperation(value="获取全部数据", notes="获取全部数据")
	public ResultJson findAll() {
		
		ResultJson resultJson = permissionService.findAll();
		return resultJson;
	}
	
	/**
	 * 获取角色用户相对应权限数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/listRolePermission")
	@RequiresPermissions("permission:listRolePermission")
	@ApiOperation(value="获取角色用户相对应权限数据", notes="获取角色用户相对应权限数据")
	public ResultJson listRolePermission(@RequestParam int page,@RequestParam int size) {
		
		ResultJson resultJson = permissionService.listRolePermission(page, size);
		return resultJson;
	}
	
	/**
	 * 添加角色权限
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@PostMapping(path = "/insertRolePermission")
	@RequiresPermissions("permission:insertRolePermission")
	@ApiOperation(value="保存角色权限数据", notes="保存角色权限数据")
	public ResultJson insertRolePermission(@RequestBody JSONObject jsonObject) {
		
		ResultJson resultJson = permissionService.insertRolePermission(jsonObject);
		return resultJson;
	}
	
	/**
	 * 删除角色权限
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@PostMapping(path = "/delRolePermission/{role_id}")
	@RequiresPermissions("permission:delRolePermission")
	@ApiOperation(value="删除该角色所有权限", notes="删除该角色所有权限")
	public ResultJson delRolePermission(@PathVariable Long role_id) {
		
		ResultJson resultJson = permissionService.delRolePermission(role_id);
		return resultJson;
	}

}
