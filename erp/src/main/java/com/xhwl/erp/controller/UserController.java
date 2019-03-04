package com.xhwl.erp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xhwl.erp.service.UserService;
import com.xhwl.erp.util.result.ResultJson;

@RestController 
@RequestMapping(path="/user")
@Api(description="用户管理(基础)")
public class UserController {
	
	@Autowired
	UserService userService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("user:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = userService.findAllByPage(pageable);
		return resultJson;
	}
	
	/**
	 * 通过组织架构返回用户信息
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findByOrganization/{o_id}")
	@RequiresPermissions("user:findByOrganization")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findByOrganization(@PathVariable Long o_id,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = userService.findByOrganization(o_id, pageable);
		return resultJson;
	}

}
