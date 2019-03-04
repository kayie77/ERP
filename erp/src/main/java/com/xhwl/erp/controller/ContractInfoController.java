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

import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.service.ContractInfoService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path="/contractInfo")
@Api(description="合同信息管理(财务)")
public class ContractInfoController {
	
	@Autowired 
	private ContractInfoService contractInfoService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("contractInfo:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = contractInfoService.findAllByPage(pageable);
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
	@RequiresPermissions("contractInfo:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = contractInfoService.findUpdateData(id);
		return resultJson;
	}
	
	/**
	 * 根据商机获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param pc_id
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAllByBussiness/{b_id}")
	@RequiresPermissions("contractInfo:findAllByBussiness")
	@ApiOperation(value="根据商机获取全部分页数据", notes="根据商机获取全部分页数据")
	public ResultJson findAllByBussiness(@PathVariable Long b_id) {
		
		ResultJson resultJson = contractInfoService.findAllByBussiness(b_id);
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
	@RequiresPermissions("contractInfo:update")
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
	@RequiresPermissions("contractInfo:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody ContractInfo contractInfo) {
		
		ResultJson resultJson = contractInfoService.save(contractInfo);
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
	@RequiresPermissions("contractInfo:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = contractInfoService.delete(id.getId());
		return resultJson;
    }
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年1月12日
	 * @return
	 */
	@PostMapping(path = "/search")
	@RequiresPermissions("contractInfo:search")
	@ApiOperation(value="数据查询", notes="数据查询")
    public ResultJson search(@ApiParam(value = "合同名称") @RequestParam(required=false) String name,@ApiParam(value = "合同编码") @RequestParam(required=false) String code,
    		@ApiParam(value = "合同所属年月") @RequestParam(required=false) Date term,@ApiParam(value = "签订时间") @RequestParam(required=false) Date signDate,
    		@ApiParam(value = "所属区域") @RequestParam(required=false) Long region_id,@ApiParam(value = "业务分类") @RequestParam(required=false) String businessCtg_name,
    		@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = contractInfoService.search(name,code, term,signDate,region_id,businessCtg_name, pageable);
		return resultJson;
    }
	
    /**
     * 数据导出
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
	@PostMapping(path = "/export")
    @RequiresPermissions("contractInfo:export")
	@ApiOperation(value="数据导出", notes="数据导出")
    public @ResponseBody ResultJson export() {
    		ResultJson resultJson = new ResultJson();
    		resultJson.setData("允许数据导出");
    		resultJson.setSuccess(true);
        return resultJson;
    }
}
