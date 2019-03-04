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

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.entity.PurchaseList;
import com.xhwl.erp.service.PurchaseListService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.bean.ObjectList;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController  
@RequestMapping(path="/purchaseList")
@Api(description="采购清单管理(成本)")
public class PurchaseListController {
	
	@Autowired 
	private PurchaseListService purchaseListService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("purchaseList:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = purchaseListService.findAllByPage(pageable);
		return resultJson;
	}
	
	/**
	 * 根据付款合同获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param pc_id
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/findAllByPaymentContract/{pc_id}")
	@RequiresPermissions("purchaseList:findAllByPaymentContract")
	@ApiOperation(value="根据付款合同获取全部分页数据", notes="根据付款合同获取全部分页数据")
	public ResultJson findAllByPaymentContractAndPage(@PathVariable Long pc_id) {
		
		ResultJson resultJson = purchaseListService.findAllByPaymentContract(pc_id);
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
	@RequiresPermissions("purchaseList:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = purchaseListService.findUpdateData(id);
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
	@RequiresPermissions("purchaseList:update")
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
	@PostMapping(path = "/importData/{pc_id}")
	@RequiresPermissions("purchaseList:importData")
	@ApiOperation(value="导入数据", notes="导入数据")
    public ResultJson importData(@RequestBody ObjectList<PurchaseList> purchaseListArrary,@PathVariable Long pc_id) {
		
		ResultJson resultJson = purchaseListService.importData(purchaseListArrary.getObjectList(),pc_id);
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
	@RequiresPermissions("purchaseList:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public PurchaseList save(@RequestBody JSONObject jsonObject) {
		
		PurchaseList  purchaseList = purchaseListService.save(jsonObject);
		return purchaseList;
    }

	/**
	 * 删除对象
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	@PostMapping(path = "/delete")
	@RequiresPermissions("purchaseList:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = purchaseListService.delete(id.getId());
		return resultJson;
    }

}
