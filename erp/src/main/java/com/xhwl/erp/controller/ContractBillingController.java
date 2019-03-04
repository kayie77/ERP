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

import com.alibaba.fastjson.JSONArray;
import com.xhwl.erp.entity.ContractBilling;
import com.xhwl.erp.service.ContractBillingService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path="/contractBilling")
@Api(description="合同开票管理(财务)")
public class ContractBillingController {
	
	@Autowired 
	private ContractBillingService contractBillingService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("contractBilling:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = contractBillingService.findAllByPage(pageable);
		return resultJson;
	}
	
	/**
	 * 根据合同信息获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月24日
	 * @param ci_id
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAllByContractInfo/{ci_id}")
	@RequiresPermissions("contractBilling:findAllByContractInfo")
	@ApiOperation(value="根据合同信息获取全部分页数据", notes="根据合同信息获取全部分页数据")
	public ResultJson findAllByContractInfoAndPage(@PathVariable Long ci_id,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = contractBillingService.findAllByContractInfoAndPage(ci_id,pageable);
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
	@RequiresPermissions("contractBilling:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = contractBillingService.findUpdateData(id);
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
	@RequiresPermissions("contractBilling:update")
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
	@RequiresPermissions("contractBilling:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody ContractBilling contractBilling) {
		
		ResultJson resultJson = contractBillingService.save(contractBilling);
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
	@RequiresPermissions("contractBilling:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = contractBillingService.delete(id.getId());
		return resultJson;
    }
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年1月12日
	 * @return
	 */
	@PostMapping(path = "/search")
	@RequiresPermissions("contractBilling:search")
	@ApiOperation(value="数据查询", notes="数据查询")
    public ResultJson search(@ApiParam(value = "发票抬头名称") @RequestParam(required=false) String name,@ApiParam(value = "发票号码") @RequestParam(required=false) String number,@ApiParam(value = "开票日期(起始)") @RequestParam(required=false) Date date,
    		@ApiParam(value = "开票日期(结束)") @RequestParam(required=false) Date date1,@ApiParam(value = "合同名称") @RequestParam(required=false) String contractInfo_name,
    		@ApiParam(value = "合同编码") @RequestParam(required=false) String contractInfo_code, @PageableDefault(value = 15) Pageable pageable) {

		ResultJson resultJson = contractBillingService.search(name, number, date, date1,contractInfo_name,contractInfo_code, pageable);
		return resultJson;
    }
	
    /**
     * 数据导出
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
	@PostMapping(path = "/export")
    @RequiresPermissions("contractBilling:export")
	@ApiOperation(value="数据导出", notes="数据导出")
    public @ResponseBody ResultJson export() {
    		ResultJson resultJson = new ResultJson();
    		resultJson.setData("允许数据导出");
    		resultJson.setSuccess(true);
        return resultJson;
    }
	
    /**
     * 数据导入
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
	@PostMapping(path = "/importData")
    @RequiresPermissions("contractBilling:importData")
	@ApiOperation(value="数据导入", notes="数据导入")
    public @ResponseBody ResultJson importData(@RequestBody JSONArray contractBillingArrary) {
    		ResultJson resultJson = contractBillingService.importData(contractBillingArrary);
        return resultJson;
    }
}
