package com.xhwl.erp.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xhwl.erp.service.CityService;
import com.xhwl.erp.util.tree.ETreeNode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path="/city")
@Api(description="城市管理(基础)")
public class CityController {
	
	@Autowired 
	private CityService cityService;
	
	@GetMapping(path = "/tree")
	@RequiresPermissions("city:tree")
	@ApiOperation(value="获取城市列表-树形结构", notes="获取城市列表-树形结构")
    public List<ETreeNode> tree() {
		
		List<ETreeNode> resultList = cityService.tree();
		return resultList;
    }
	
}
