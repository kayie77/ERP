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

import com.xhwl.erp.entity.InboundDetaile;
import com.xhwl.erp.service.InboundDetaileService;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController  
@RequestMapping(path="/inboundDetaile")
@Api(description="入库明细管理(成本)")
public class InboundDetaileController {
	
	@Autowired 
	private InboundDetaileService inboundDetaileService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月23日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("inboundDetaile:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = inboundDetaileService.findAllByPage(pageable);
		return resultJson;
	}
	
	/**
	 * 根据入库单获得获取全部数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param pc_id
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAllByPaymentContract/{pc_id}")
	@RequiresPermissions("inboundDetaile:findAllByPaymentContract")
	@ApiOperation(value="根据付款合同获取全部分页数据", notes="根据付款合同获取全部分页数据")
	public ResultJson findAllByPaymentContract(@PathVariable Long pc_id) {
		
		ResultJson resultJson = inboundDetaileService.findAllByPaymentContract(pc_id);
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
	@RequiresPermissions("inboundDetaile:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody InboundDetaile inboundDetaile) {
		
		ResultJson resultJson = inboundDetaileService.save(inboundDetaile);
		return resultJson;
    }
}
