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

import com.xhwl.erp.entity.PaymentContract;
import com.xhwl.erp.service.PaymentContractService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController  
@RequestMapping(path="/paymentContract")
@Api(description="付款合同管理(成本)")
public class PaymentContractController {
	
	@Autowired 
	private PaymentContractService paymentContractService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月23日
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAllByPage/{role_code}")
	@RequiresPermissions("paymentContract:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(@PathVariable String role_code,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = paymentContractService.findAllByPage(role_code,pageable);
		return resultJson;
	}
	
	/**
	 * 获取金额统计
	 * @author jiayiwu
	 * @date 2018年1月23日
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/getTotalAmount/{role_code}")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public JSONObject getTotalAmount(@PathVariable String role_code) {
		
		JSONObject JSONObject = paymentContractService.getTotalAmount(role_code);
		return JSONObject;
	}
	
	/**
	 * 查看明细
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/findUpdateData/{id}")
	@RequiresPermissions("paymentContract:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = paymentContractService.findUpdateData(id);
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
	@RequiresPermissions("paymentContract:update")
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
	@RequiresPermissions("paymentContract:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody PaymentContract paymentContract) {
		
		ResultJson resultJson = paymentContractService.save(paymentContract);
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
	@RequiresPermissions("paymentContract:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = paymentContractService.delete(id.getId());
		return resultJson;
    }
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年1月12日
	 * @return
	 */
	@PostMapping(path = "/search")
	@RequiresPermissions("paymentContract:search")
	@ApiOperation(value="数据查询", notes="数据查询")
    public ResultJson search(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "订单编号") @RequestParam(required=false) String orderCode,@ApiParam(value = "付款合同编号") @RequestParam(required=false) String code,
    		@ApiParam(value = "使用项目") @RequestParam(required=false) String project,@ApiParam(value = "使用部门") @RequestParam(required=false) String department,
    		@ApiParam(value = "业务分类") @RequestParam(required=false) String b_ctg_name,@ApiParam(value = "材料分类") @RequestParam(required=false) String m_ctg_name,
    		@ApiParam(value = "发货状态") @RequestParam(required=false) String deliveryStatus,@ApiParam(value = "合同编号") @RequestParam(required=false) String contractInfo_code,@ApiParam(value = "合同名称") @RequestParam(required=false) String contractInfo_name,
    		@ApiParam(value = "申请日期(开始)") @RequestParam(required=false) Date applicationTime,@ApiParam(value = "申请日期(结束)") @RequestParam(required=false) Date applicationTime1,
    		@ApiParam(value = "计划采购金额(开始)") @RequestParam(required=false) double adAmount,@ApiParam(value = "计划采购金额(结束)") @RequestParam(required=false) double adAmount1,
    		@ApiParam(value = "实际采购金额(开始)") @RequestParam(required=false) double acAmount,@ApiParam(value = "实际采购金额(结束)") @RequestParam(required=false) double acAmount1,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = paymentContractService.search(role_code,orderCode,code,project,department,b_ctg_name,m_ctg_name,deliveryStatus,contractInfo_code,contractInfo_name,applicationTime,applicationTime1,adAmount,adAmount1,acAmount,acAmount1,pageable);
		return resultJson;
    }
	
    /**
     * 数据导出
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
	@PostMapping(path = "/export")
    @RequiresPermissions("paymentContract:export")
	@ApiOperation(value="数据导出", notes="数据导出")
    public @ResponseBody ResultJson export() {
    		ResultJson resultJson = new ResultJson();
    		resultJson.setData("允许数据导出");
    		resultJson.setSuccess(true);
        return resultJson;
    }

}
