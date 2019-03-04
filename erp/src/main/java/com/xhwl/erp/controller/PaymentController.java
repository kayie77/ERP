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

import com.xhwl.erp.entity.Payment;
import com.xhwl.erp.service.PaymentService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path="/payment")
@Api(description="付款合同 - 付款信息管理(成本)")
public class PaymentController {
	
	@Autowired 
	private PaymentService paymentService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("payment:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = paymentService.findAllByPage(pageable);
		return resultJson;
	}
	
	/**
	 * 根据付款合同获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月24日
	 * @param pc_id
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAllByPaymentContract/{pc_id}")
	@RequiresPermissions("payment:findAllByPaymentContract")
	@ApiOperation(value="根据付款合同获取全部分页数据", notes="根据付款合同获取全部分页数据")
	public ResultJson findAllByPaymentContractAndPage(@PathVariable Long pc_id,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = paymentService.findAllByPaymentContractAndPage(pc_id,pageable);
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
	@RequiresPermissions("payment:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = paymentService.findUpdateData(id);
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
	@RequiresPermissions("payment:update")
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
	@RequiresPermissions("payment:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody Payment payment) {
		
		ResultJson resultJson = paymentService.save(payment);
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
	@RequiresPermissions("payment:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = paymentService.delete(id.getId());
		return resultJson;
    }
}
