package com.xhwl.erp.controller;

import java.sql.Date;

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

import com.xhwl.erp.entity.Business;
import com.xhwl.erp.service.BusinessService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController  
@RequestMapping(path="/bussiness")
@Api(description="商机管理(市场)")
public class BussinessController {
	
	@Autowired 
	private BusinessService businessService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAllByPage/{role_code}")
	@RequiresPermissions("bussiness:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(@PathVariable String role_code,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = businessService.findAllByPage(role_code,pageable);
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
	@RequiresPermissions("bussiness:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = businessService.findUpdateData(id);
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
	@RequiresPermissions("bussiness:update")
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
	@RequiresPermissions("bussiness:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody Business business) {
		
		ResultJson resultJson = businessService.save(business);
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
	@RequiresPermissions("bussiness:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = businessService.delete(id.getId());
		return resultJson;
    }
	
	/**
	 * 商机审核
	 * @author jiayiwu
	 * @date 2018年3月15日
	 * @param id
	 * @return
	 */
	@PostMapping(path = "/examine/{id}")
	@RequiresPermissions("bussiness:examine")
	@ApiOperation(value="商机审核", notes="商机审核")
    public ResultJson examine(@PathVariable Long id) {
		
		ResultJson resultJson = businessService.examine(id);
		return resultJson;
    }
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年1月12日
	 * @return
	 */
	@PostMapping(path = "/search")
	@RequiresPermissions("bussiness:search")
	@ApiOperation(value="数据查询", notes="数据查询")
    public ResultJson search(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "商机名称") @RequestParam(required=false) String name,@ApiParam(value = "商机编码") @RequestParam(required=false) String code,@ApiParam(value = "商机提供人") @RequestParam(required=false) String sourcePerson,
    		@ApiParam(value = "项目预计启动时间(开始)") @RequestParam(required=false) Date startDate,@ApiParam(value = "项目预计启动时间(结束)") @RequestParam(required=false) Date startDate1,@ApiParam(value = "预计成交金额(开始)") @RequestParam(required=false) double amount,@ApiParam(value = "预计成交金额(结束)") @RequestParam(required=false) double amount1,
    		@ApiParam(value = "商机执行状态") @RequestParam(required=false) String executState,@ApiParam(value = "商机审批状态") @RequestParam(required=false) String examineState,
    		@ApiParam(value = "商机录入日期(开始)") @RequestParam(required=false) Date date,@ApiParam(value = "商机录入日期(结束)") @RequestParam(required=false) Date date1,
    		@ApiParam(value = "所属城市") @RequestParam(required=false) Long city_id,@ApiParam(value = "客户名称") @RequestParam(required=false) String client_name,@ApiParam(value = "客户类别") @RequestParam(required=false) String client_ctg,
    		@ApiParam(value = "业务分类") @RequestParam(required=false) String bussinesCtg_name,@ApiParam(value = "业务负责人") @RequestParam(required=false) Long bp_id,@ApiParam(value = "所属区域") @RequestParam(required=false) Long region_id,
    		@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = businessService.search(role_code,name,code,sourcePerson,startDate,startDate1,amount,amount1,executState,examineState,date,date1,city_id,client_name,client_ctg,bussinesCtg_name,bp_id,region_id,pageable);
		return resultJson;
    }

    /**
     * 数据导出
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
	@PostMapping(path = "/export")
    @RequiresPermissions("bussiness:export")
	@ApiOperation(value="数据导出", notes="数据导出")
    public @ResponseBody ResultJson export() {
    		ResultJson resultJson = new ResultJson();
    		resultJson.setData("允许数据导出");
    		resultJson.setSuccess(true);
        return resultJson;
    }
}
