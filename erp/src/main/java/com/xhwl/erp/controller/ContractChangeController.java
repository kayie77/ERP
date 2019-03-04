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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xhwl.erp.service.ContractChangeService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path="/contractChange")
@Api(description="合同变更管理(财务)")
public class ContractChangeController {
	
	@Autowired 
	private ContractChangeService contractChangeService;
	
	/**
	 * 根据合同信息获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月24日
	 * @param ci_id
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAllByContractInfo/{ci_id}")
	@RequiresPermissions("contractChange:findAllByContractInfo")
	@ApiOperation(value="根据合同信息获取全部分页数据", notes="根据合同信息获取全部分页数据")
	public ResultJson findAllByContractInfo(@PathVariable Long ci_id,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = contractChangeService.findAllByContractInfo(ci_id,pageable);
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
	@RequiresPermissions("contractChange:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = contractChangeService.findUpdateData(id);
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
	@RequiresPermissions("contractChange:update")
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
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@ApiParam(value = "唯一标识id")@RequestParam(required=false) Long id,@ApiParam(value = "附件说明")@RequestParam(required=false) String describtion,
    		@ApiParam(value = "上传人")@RequestParam String person,@ApiParam(value = "合同基础信息id")@RequestParam("ci_id") Long ci_id,@RequestParam("file") MultipartFile file) {

		ResultJson resultJson = contractChangeService.save(id,describtion,person,ci_id,file);
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
	@RequiresPermissions("contractChange:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = contractChangeService.delete(id.getId());
		return resultJson;
    }
}
