package com.xhwl.erp.service;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.util.result.ResultJson;

public interface PermissionService {
	
	/**
	 * 获取全部数据
	 * @author jiayiwu
	 * @date 2018年4月12日
	 * @return
	 */
	ResultJson findAll();
	
	/**
	 * 获取角色用户相对应权限数据
	 * @author jiayiwu
	 * @date 2018年4月12日
	 * @return
	 */
	ResultJson listRolePermission(int page, int size);
	
	/**
	 * 插入角色权限数据
	 * @author jiayiwu
	 * @date 2018年4月12日
	 * @return
	 */
	ResultJson insertRolePermission(JSONObject jsonObject);
	
	/**
	 * 删除该角色所有权限
	 * @author jiayiwu
	 * @date 2018年4月12日
	 * @param jsonObject
	 * @return
	 */
	ResultJson delRolePermission(Long role_id);
	
    /**
     * 查询某用户的 角色  菜单列表   权限列表
     *
     * @param username
     * @return
     */
	JSONObject getUserPermission(String username);
}
