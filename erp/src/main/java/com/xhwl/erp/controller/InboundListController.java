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

import com.xhwl.erp.entity.InboundList;
import com.xhwl.erp.service.InboundListService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController  
@RequestMapping(path="/inboundList")
@Api(description="入库单管理(成本)")
public class InboundListController {
	
	@Autowired 
	private InboundListService inboundListService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月23日
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAllByPage/{role_code}")
	@RequiresPermissions("inboundList:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(@PathVariable String role_code,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = inboundListService.findAllByPage(role_code,pageable);
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
	@RequiresPermissions("inboundList:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = inboundListService.findUpdateData(id);
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
	@RequiresPermissions("inboundList:update")
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
	@RequiresPermissions("inboundList:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody InboundList inboundList) {
		
		ResultJson resultJson = inboundListService.save(inboundList);
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
	@RequiresPermissions("inboundList:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = inboundListService.delete(id.getId());
		return resultJson;
    }
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年1月12日
	 * @return
	 */
	@PostMapping(path = "/search")
	@RequiresPermissions("inboundList:search")
	@ApiOperation(value="数据查询", notes="数据查询")
    public ResultJson rkSearch(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "订单编号") @RequestParam(required=false) String orderCode,@ApiParam(value = "入库单编号") @RequestParam(required=false) String code,
    		@ApiParam(value = "入库日期(开始)") @RequestParam(required=false) Date date,@ApiParam(value = "入库日期(结束)") @RequestParam(required=false) Date date1,
    		@ApiParam(value = "使用部门") @RequestParam(required=false) String department,@ApiParam(value = "供应商名称") @RequestParam(required=false) String supply_name,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = inboundListService.search(role_code,orderCode,code,date,date1,department,supply_name,pageable);
		return resultJson;
    }
	
    /**
     * 数据导出
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
	@PostMapping(path = "/export")
    @RequiresPermissions("inboundList:export")
	@ApiOperation(value="数据导出", notes="数据导出")
    public @ResponseBody ResultJson export() {
    		ResultJson resultJson = new ResultJson();
    		resultJson.setData("允许数据导出");
    		resultJson.setSuccess(true);
        return resultJson;
    }

}
