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

import com.xhwl.erp.entity.ContractSchedule;
import com.xhwl.erp.service.ContractScheduleService;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path="/contractSchedule")
@Api(description="合同进度管理(财务)")
public class ContractScheduleController {
	
	@Autowired 
	private ContractScheduleService contractScheduleService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("contractSchedule:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = contractScheduleService.findAllByPage(pageable);
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
	@RequiresPermissions("contractSchedule:findAllByContractInfo")
	@ApiOperation(value="根据合同信息获取全部数据", notes="根据合同信息获取全部数据")
	public ResultJson findAllByPaymentContractAndPage(@PathVariable Long ci_id) {
		
		ResultJson resultJson = contractScheduleService.findAllByContractInfo(ci_id);
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
	@RequiresPermissions("contractSchedule:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = contractScheduleService.findUpdateData(id);
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
	@RequiresPermissions("contractSchedule:update")
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
	@RequiresPermissions("contractSchedule:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody ContractSchedule contractSchedule) {
		
		ResultJson resultJson = contractScheduleService.save(contractSchedule);
		return resultJson;
    }
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年1月12日
	 * @return
	 */
	@PostMapping(path = "/search")
	@RequiresPermissions("contractSchedule:search")
	@ApiOperation(value="数据查询", notes="数据查询")
    public ResultJson search(@ApiParam(value = "项目所属阶段") @RequestParam(required=false) String stage,@ApiParam(value = "项目状态") @RequestParam(required=false) String projectStatus,
    		@ApiParam(value = "回款状态") @RequestParam(required=false) String receivedStatus,@ApiParam(value = "材料进度匹配度") @RequestParam(required=false) String materialStatus,
    		@ApiParam(value = "人工进度匹配度") @RequestParam(required=false) String artificialStatus,@ApiParam(value = "综合进度匹配度") @RequestParam(required=false) String comprehensiveStatus,
    		@ApiParam(value = "收支差异状态") @RequestParam(required=false) String paymentBalanceStatus,@ApiParam(value = "付现差异状态") @RequestParam(required=false) String cashBalanceStatus,
    		@ApiParam(value = "合同名称") @RequestParam(required=false) String contractInfo_name,@ApiParam(value = "合同编码") @RequestParam(required=false) String contractInfo_code, @PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = contractScheduleService.search(stage, projectStatus, receivedStatus, materialStatus,  artificialStatus, comprehensiveStatus, paymentBalanceStatus, cashBalanceStatus, contractInfo_name,contractInfo_code, pageable);
		return resultJson;
    }
	
    /**
     * 数据导出
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
	@PostMapping(path = "/export")
    @RequiresPermissions("contractSchedule:export")
	@ApiOperation(value="数据导出", notes="数据导出")
    public @ResponseBody ResultJson export() {
    		ResultJson resultJson = new ResultJson();
    		resultJson.setData("允许数据导出");
    		resultJson.setSuccess(true);
        return resultJson;
    }
}
