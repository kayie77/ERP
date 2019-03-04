package com.xhwl.erp.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xhwl.erp.service.OrganizationService;
import com.xhwl.erp.util.tree.ETreeNode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController  
@RequestMapping(path="/org")
@Api(description="组织架构管理(基础)")
public class OrganizationController {
	
	@Autowired 
	private OrganizationService organizationService;
	
	@GetMapping(path = "/tree")
	@RequiresPermissions("org:tree")
	@ApiOperation(value="获取组织架构列表-树形结构", notes="获取组织架构列表-树形结构")
    public List<ETreeNode> tree() {
		
		List<ETreeNode> resultList = organizationService.tree();
		return resultList;
    }

}
