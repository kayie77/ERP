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

import com.alibaba.fastjson.JSONArray;
import com.xhwl.erp.entity.ContractReceived;
import com.xhwl.erp.service.ContractReceivedService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path="/contractReceived")
@Api(description="合同回款管理(财务)")
public class ContractReceivedController {
	
	@Autowired 
	private ContractReceivedService contractReceivedService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("contractReceived:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = contractReceivedService.findAllByPage(pageable);
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
	@RequiresPermissions("contractReceived:findAllByContractInfo")
	@ApiOperation(value="根据合同信息获取全部分页数据", notes="根据合同信息获取全部分页数据")
	public ResultJson findAllByContractInfo(@PathVariable Long ci_id) {
		
		ResultJson resultJson = contractReceivedService.findAllByContractInfo(ci_id);
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
	@RequiresPermissions("contractReceived:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = contractReceivedService.findUpdateData(id);
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
	@RequiresPermissions("contractReceived:update")
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
	@RequiresPermissions("contractReceived:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody ContractReceived contractReceived) {
		
		ResultJson resultJson = contractReceivedService.save(contractReceived);
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
	@RequiresPermissions("contractReceived:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = contractReceivedService.delete(id.getId());
		return resultJson;
    }
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年1月12日
	 * @return
	 */
	@PostMapping(path = "/search")
	@RequiresPermissions("contractReceived:search")
	@ApiOperation(value="数据查询", notes="数据查询")
    public ResultJson search(@ApiParam(value = "合同名称") @RequestParam(required=false) String contractInfo_name,@ApiParam(value = "合同编码") @RequestParam(required=false) String contractInfo_code, @PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = contractReceivedService.search(contractInfo_name,contractInfo_code, pageable);
		return resultJson;
    }
	
    /**
     * 数据导出
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
	@PostMapping(path = "/export")
    @RequiresPermissions("contractReceived:export")
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
    @RequiresPermissions("contractReceived:importData")
	@ApiOperation(value="数据导入", notes="数据导入")
    public @ResponseBody ResultJson importData(@RequestBody JSONArray contractReceivedArrary) {
    		ResultJson resultJson = contractReceivedService.importData(contractReceivedArrary);
        return resultJson;
    }
}
