package com.xhwl.erp.controller;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.entity.BusinessHistory;
import com.xhwl.erp.service.BusinessHistoryService;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController  
@RequestMapping(path="/businessHistory")
@Api(description="商机修改日志(市场)")
public class BusinessHistoryController {
	
	@Autowired
	private BusinessHistoryService businessHistoryService;
	
	/**
	 * 根据商机获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param pc_id
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAllByBusiness/{b_id}")
	@RequiresPermissions("businessHistory:findAllByBusiness")
	@ApiOperation(value="根据商机获取全部分页数据", notes="根据商机获取全部分页数据")
	public ResultJson findAllByPrice(@PathVariable Long b_id,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = businessHistoryService.findAllByBusiness(b_id,pageable);
		return resultJson;
	}
	
	/**
	 * 保存对象
	 * @author jiayiwu
	 * @date 2018年1月3日
	 * @param business
	 * @return
	 */
	@PostMapping(path = "/save")
	@RequiresPermissions("businessHistory:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public BusinessHistory save(@RequestBody JSONObject jsonObject) {
		
		BusinessHistory businessHistory = businessHistoryService.save(jsonObject);
		return businessHistory;
    }
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年1月12日
	 * @return
	 */
	@PostMapping(path = "/search")
	@RequiresPermissions("businessHistory:search")
	@ApiOperation(value="数据查询", notes="数据查询")
    public ResultJson search(@ApiParam(value = "修改内容") @RequestParam(required=false) String content,@ApiParam(value = "修改时间（开始）") @RequestParam(required=false) Date time,
    		@ApiParam(value = "修改时间（结束）") @RequestParam(required=false) Date time1,@ApiParam(value = "所属商机") @RequestParam(required=false) Long b_id,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = businessHistoryService.search(b_id,content,time,time1,pageable);
		return resultJson;
    }

}
