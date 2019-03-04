package com.xhwl.erp.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.entity.PriceHistory;
import com.xhwl.erp.service.PriceHistoryService;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController  
@RequestMapping(path="/priceHistory")
@Api(description="价格体系修改历史(成本)")
public class PriceHistoryController {
	
	@Autowired
	private PriceHistoryService priceHistoryService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月23日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("priceHistory:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = priceHistoryService.findAllByPage(pageable);
		return resultJson;
	}
	
	/**
	 * 根据价格体系获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param pc_id
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAllByPrice/{p_id}")
	@RequiresPermissions("priceHistory:findAllByPrice")
	@ApiOperation(value="根据价格体系获取全部分页数据", notes="根据价格体系获取全部分页数据")
	public ResultJson findAllByPrice(@PathVariable Long p_id,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = priceHistoryService.findAllByPrice(p_id,pageable);
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
	@RequiresPermissions("priceHistory:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public PriceHistory save(@RequestBody JSONObject jsonObject) {
		
		PriceHistory priceHistory = priceHistoryService.save(jsonObject);
		return priceHistory;
    }

}
