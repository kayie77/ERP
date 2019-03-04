package com.xhwl.erp.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xhwl.erp.entity.OutboundCheck;
import com.xhwl.erp.service.OutboundCheckService;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController  
@RequestMapping(path="/outboundCheck")
@Api(description="出库审核管理(成本)")
public class OutboundCheckController {
	
	@Autowired 
	private OutboundCheckService outboundCheckService;
	
	/**
	 * 根据付款合同获得获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param pc_id
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findByOutboundList/{ol_id}")
	@RequiresPermissions("outboundCheck:findByOutboundList")
	@ApiOperation(value="根据出库清单获取数据", notes="根据出库清单获取数据")
	public ResultJson findByOutboundList(@PathVariable Long ol_id) {
		
		ResultJson resultJson = outboundCheckService.findByOutboundList(ol_id);
		return resultJson;
	}
	
	/**
	 * 提交审核
	 * @author jiayiwu
	 * @date 2018年4月28日
	 * @param outboundCheck
	 * @return
	 */
	@PostMapping(path = "/submit")
	@RequiresPermissions("outboundCheck:submit")
	@ApiOperation(value="提交审核", notes="提交审核")
    public ResultJson submit(@RequestBody OutboundCheck outboundCheck) {
		
		ResultJson resultJson = outboundCheckService.submit(outboundCheck);
		return resultJson;
    }
	
	/**
	 * 办事处经理审核
	 * @author jiayiwu
	 * @date 2018年4月28日
	 * @param outboundCheck
	 * @return
	 */
	@PostMapping(path = "/officeCheck")
	@RequiresPermissions("outboundCheck:officeCheck")
	@ApiOperation(value="办事处经理审核", notes="办事处经理审核")
    public ResultJson officeCheck(@RequestBody OutboundCheck outboundCheck) {
		
		ResultJson resultJson = outboundCheckService.officeCheck(outboundCheck);
		return resultJson;
    }
	
	/**
	 * 办成本部审核
	 * @author jiayiwu
	 * @date 2018年4月28日
	 * @param outboundCheck
	 * @return
	 */
	@PostMapping(path = "/costCheck")
	@RequiresPermissions("outboundCheck:costCheck")
	@ApiOperation(value="成本部审核", notes="成本部审核")
    public ResultJson costCheck(@RequestBody OutboundCheck outboundCheck) {
		
		ResultJson resultJson = outboundCheckService.costCheck(outboundCheck);
		return resultJson;
    }
}
