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

import com.xhwl.erp.entity.PaymentPlan;
import com.xhwl.erp.service.PaymentPlanService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path="/paymentPlan")
@Api(description="合同交底 - 回款计划管理(财务)")
public class PaymentPlanController {
	
	@Autowired 
	private PaymentPlanService paymentPlanService;
	
	/**
	 * 根据合同交底id获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月24日
	 * @param pc_id
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAllByContractBasis/{cb_id}")
	@RequiresPermissions("paymentPlan:findAllByContractBasis")
	@ApiOperation(value="根据合同交底id获取全部分页数据", notes="根据合同交底id获取全部分页数据")
	public ResultJson findAllByContractBasisAndPage(@PathVariable Long cb_id,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = paymentPlanService.findAllByContractBasisAndPage(cb_id, pageable);
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
	@RequiresPermissions("paymentPlan:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = paymentPlanService.findUpdateData(id);
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
	@RequiresPermissions("paymentPlan:update")
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
	 * @date 2018年1月8日
	 * @param contractBilling
	 * @return
	 */
	@PostMapping(path = "/save")
	@RequiresPermissions("paymentPlan:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody PaymentPlan paymentPlan) {
		
		ResultJson resultJson = paymentPlanService.save(paymentPlan);
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
	@RequiresPermissions("paymentPlan:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = paymentPlanService.delete(id.getId());
		return resultJson;
    }
}