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

import com.xhwl.erp.entity.InboundCheck;
import com.xhwl.erp.service.InboundCheckService;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController  
@RequestMapping(path="/inboundCheck")
@Api(description="入库审核管理(成本)")
public class InboundCheckController {
	
	@Autowired 
	private InboundCheckService inboundCheckService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月23日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("inboundCheck:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = inboundCheckService.findAllByPage(pageable);
		return resultJson;
	}
	
	/**
	 * 根据付款合同获得获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param pc_id
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findByInboundList/{il_id}")
	@RequiresPermissions("inboundCheck:findByInboundList")
	@ApiOperation(value="根据入库单获取数据", notes="根据入库单获取数据")
	public ResultJson findByInboundList(@PathVariable Long il_id) {
		
		ResultJson resultJson = inboundCheckService.findByInboundList(il_id);
		return resultJson;
	}
	
	/**
	 * 提交审核
	 * @author jiayiwu
	 * @date 2018年4月28日
	 * @param inboundCheck
	 * @return
	 */
	@PostMapping(path = "/submit")
	@RequiresPermissions("inboundCheck:submit")
	@ApiOperation(value="提交审核", notes="提交审核")
    public ResultJson save(@RequestBody InboundCheck inboundCheck) {
		
		ResultJson resultJson = inboundCheckService.submit(inboundCheck);
		return resultJson;
    }
	
	/**
	 * 办事处经理审核
	 * @author jiayiwu
	 * @date 2018年4月28日
	 * @param inboundCheck
	 * @return
	 */
	@PostMapping(path = "/officeCheck")
	@RequiresPermissions("inboundCheck:officeCheck")
	@ApiOperation(value="办事处经理审核", notes="办事处经理审核")
    public ResultJson officeCheck(@RequestBody InboundCheck inboundCheck) {
		
		ResultJson resultJson = inboundCheckService.officeCheck(inboundCheck);
		return resultJson;
    }
	
	/**
	 * 本部成本部审核
	 * @author jiayiwu
	 * @date 2018年4月28日
	 * @param inboundCheck
	 * @return
	 */
	@PostMapping(path = "/costCheck")
	@RequiresPermissions("inboundCheck:costCheck")
	@ApiOperation(value="成本部审核", notes="成本部审核")
    public ResultJson costCheck(@RequestBody InboundCheck inboundCheck) {
		
		ResultJson resultJson = inboundCheckService.costCheck(inboundCheck);
		return resultJson;
    }
}
