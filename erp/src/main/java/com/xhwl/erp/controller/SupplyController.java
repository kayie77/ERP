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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xhwl.erp.entity.Supply;
import com.xhwl.erp.service.SupplyService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController  
@RequestMapping(path="/supply")
@Api(description="供应商管理(成本)")
public class SupplyController {
	
	@Autowired 
	private SupplyService supplyService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月23日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("supply:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = supplyService.findAllByPage(pageable);
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
	@RequiresPermissions("supply:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = supplyService.findUpdateData(id);
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
	@RequiresPermissions("supply:update")
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
	@RequiresPermissions("supply:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody Supply supply) {
		
		ResultJson resultJson = supplyService.save(supply);
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
	@RequiresPermissions("supply:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = supplyService.delete(id.getId());
		return resultJson;
    }
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年1月12日
	 * @return
	 */
	@PostMapping(path = "/search")
	@RequiresPermissions("supply:search")
	@ApiOperation(value="数据查询", notes="数据查询")
    public ResultJson search(@ApiParam(value = "供应商名称") @RequestParam(required=false) String name,@ApiParam(value = "物资类别") @RequestParam(required=false) String materialCategory,
    		@ApiParam(value = "企业性质") @RequestParam(required=false) String enterpriseNature,@ApiParam(value = "合作商类型") @RequestParam(required=false) String cooperativeType,
    		@ApiParam(value = "供应商类别") @RequestParam(required=false) String category,@ApiParam(value = "供应商等级") @RequestParam(required=false) String grade,
    		@ApiParam(value = "供应商类型") @RequestParam(required=false) String type,@ApiParam(value = "供货周期") @RequestParam(required=false) String supplyCycle,
    		@ApiParam(value = "评审状态") @RequestParam(required=false) String reviewState,@ApiParam(value = "结算方式") @RequestParam(required=false) String settlementMethod,
    		@ApiParam(value = "供应区域") @RequestParam(required=false) Long region_id,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = supplyService.search(name, materialCategory, enterpriseNature, cooperativeType, category,grade,type,supplyCycle ,reviewState,settlementMethod,region_id,pageable);
		return resultJson;
    }
	
    /**
     * 数据导出
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
	@PostMapping(path = "/export")
    @RequiresPermissions("supply:export")
	@ApiOperation(value="数据导出", notes="数据导出")
    public @ResponseBody ResultJson export() {
    		ResultJson resultJson = new ResultJson();
    		resultJson.setData("允许数据导出");
    		resultJson.setSuccess(true);
        return resultJson;
    }

}
