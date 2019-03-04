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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xhwl.erp.entity.Price;
import com.xhwl.erp.service.PriceService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController  
@RequestMapping(path="/price")
@Api(description="价格体系管理(成本)")
public class PriceController {
	
	@Autowired 
	private PriceService priceService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月23日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("price:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = priceService.findAllByPage(pageable);
		return resultJson;
	}
	
	/**
	 * 查看明细
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/findUpdateData/{id}")
	@RequiresPermissions("price:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = priceService.findUpdateData(id);
		return resultJson;
	}
	
	/**
	 *编辑 
	 * @author jiayiwu
	 * @date 2018年4月20日
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/update")
	@RequiresPermissions("price:update")
	@ApiOperation(value="编辑 ", notes="编辑 ")
	public ResultJson update(@PathVariable Long id) {
		ResultJson resultJson = new ResultJson();
		resultJson.setData("允许编辑");
		resultJson.setSuccess(true);
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
	@RequiresPermissions("price:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody Price price) {
		
		ResultJson resultJson = priceService.save(price);
		return resultJson;
    }

	/**
	 * 删除对象
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	@PostMapping(path = "/delete")
	@RequiresPermissions("price:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = priceService.delete(id.getId());
		return resultJson;
    }
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年1月12日
	 * @return
	 */
	@PostMapping(path = "/search")
	@RequiresPermissions("price:search")
	@ApiOperation(value="数据查询", notes="数据查询")
    public ResultJson search(@ApiParam(value = "产品名称") @RequestParam(required=false) String name,@ApiParam(value = "产品编码") @RequestParam(required=false) String code,
    		@ApiParam(value = "产品类型") @RequestParam(required=false) String type,@ApiParam(value = "系统") @RequestParam(required=false) String system,
    		@ApiParam(value = "规格型号") @RequestParam(required=false) String specModel, @ApiParam(value = "品牌") @RequestParam(required=false) String brand,
    		@ApiParam(value = "有效期限(开始)") @RequestParam(required=false) Date startDate, @ApiParam(value = "有效期限(结束)") @RequestParam(required=false) Date endDate,
    		@ApiParam(value = "供应商名称️") @RequestParam(required=false) String supply_name,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = priceService.search(name, code, type, system, specModel, brand ,startDate, endDate,supply_name, pageable);
		return resultJson;
    }
	
    /**
     * 数据导出
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
	@PostMapping(path = "/export")
    @RequiresPermissions("price:export")
	@ApiOperation(value="数据导出", notes="数据导出")
    public @ResponseBody ResultJson export() {
    		ResultJson resultJson = new ResultJson();
    		resultJson.setData("允许数据导出");
    		resultJson.setSuccess(true);
        return resultJson;
    }

}
