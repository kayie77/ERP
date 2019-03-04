package com.xhwl.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.service.KeywordQueryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController  
@RequestMapping(path="/keywordQuery")
@Api(description="动态模糊检索(系统)")
public class KeywordQueryController {
	
	@Autowired 
	private KeywordQueryService keywordQueryService;
	
	
	/**
	 * 动态模糊检索-客户名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @return
	 */
	@GetMapping(path = "/clientName")
	@ApiOperation(value="客户名称", notes="客户名称")
	public JSONObject clientName(@ApiParam(value = "客户名称") @RequestParam(required=false) String clientName) {
		
		JSONObject jsonobject = keywordQueryService.clientName(clientName);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-用户名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/userName")
	@ApiOperation(value="用户名称", notes="用户名称")
	public JSONObject userName(@ApiParam(value = "用户名称") @RequestParam(required=false) String userName) {
		
		JSONObject jsonobject = keywordQueryService.userName(userName);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-商机名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/bussinessName")
	@ApiOperation(value="商机名称", notes="商机名称")
	public JSONObject bussinessName(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "商机名称") @RequestParam(required=false) String businessName) {
		
		JSONObject jsonobject = keywordQueryService.businessName(role_code,businessName);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-商机编码
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/bussinessCode")
	@ApiOperation(value="商机编码", notes="商机编码")
	public JSONObject bussinessCode(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "商机编码") @RequestParam(required=false) String bussinessCode) {
		
		JSONObject jsonobject = keywordQueryService.businessCode(role_code,bussinessCode);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-合同名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/contractInfoName")
	@ApiOperation(value="合同名称", notes="合同名称")
	public JSONObject contractInfoName(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "合同名称") @RequestParam(required=false) String contractInfoName) {
		
		JSONObject jsonobject = keywordQueryService.contractInfoName(role_code,contractInfoName);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-合同编码
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/contractInfoCode")
	@ApiOperation(value="合同编码", notes="合同编码")
	public JSONObject contractInfoCode(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "合同编码") @RequestParam(required=false) String contractInfoCode) {
		
		JSONObject jsonobject = keywordQueryService.contractInfoCode(role_code,contractInfoCode);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-开票号码
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/contractBillingNumber")
	@ApiOperation(value="开票号码", notes="开票号码")
	public JSONObject contractBillingNumber(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "开票号码") @RequestParam(required=false) String contractBillingNumber) {
		
		JSONObject jsonobject = keywordQueryService.contractBillingNumber(role_code,contractBillingNumber);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-付款合同号
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/paymentContractCode")
	@ApiOperation(value="付款合同号", notes="付款合同号")
	public JSONObject paymentContractCode(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "付款合同编码") @RequestParam(required=false) String paymentContractCode) {
		
		JSONObject jsonobject = keywordQueryService.paymentContractCode(role_code,paymentContractCode);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-订单编号
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/orderCode")
	@ApiOperation(value="订单编号", notes="订单编号")
	public JSONObject orderCode(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "订单编号") @RequestParam(required=false) String orderCode) {
		
		JSONObject jsonobject = keywordQueryService.orderCode(role_code,orderCode);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-入库单号
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/inboundListCode")
	@ApiOperation(value="入库单号", notes="入库单号")
	public JSONObject inboundListCode(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "入库单号") @RequestParam(required=false) String inboundListCode) {
		
		JSONObject jsonobject = keywordQueryService.inboundListCode(role_code,inboundListCode);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-出库单号
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/outboundListCode")
	@ApiOperation(value="出库单号", notes="出库单号")
	public JSONObject outboundListCode(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "出库单号") @RequestParam(required=false) String outboundListCode) {
		
		JSONObject jsonobject = keywordQueryService.outboundListCode(role_code,outboundListCode);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-项目名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/projectName")
	@ApiOperation(value="项目名称", notes="项目名称")
	public JSONObject projectName(@ApiParam(value = "角色编码") @RequestParam(required=false) String role_code,@ApiParam(value = "项目名称") @RequestParam(required=false) String projectName) {
		
		JSONObject jsonobject = keywordQueryService.projectName(role_code,projectName);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-供应商名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/supplyName")
	@ApiOperation(value="供应商名称", notes="供应商名称")
	public JSONObject supplyName(@ApiParam(value = "供应商名称") @RequestParam(required=false) String supplyName) {
		
		JSONObject jsonobject = keywordQueryService.supplyName(supplyName);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-产品名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/priceName")
	@ApiOperation(value="产品名称", notes="产品名称")
	public JSONObject priceName(@ApiParam(value = "产品名称") @RequestParam(required=false) String priceName) {
		
		JSONObject jsonobject = keywordQueryService.priceName(priceName);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-产品编码
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/priceCode")
	@ApiOperation(value="产品编码", notes="产品编码")
	public JSONObject priceCode(@ApiParam(value = "产品编码") @RequestParam(required=false) String priceCode) {
		
		JSONObject jsonobject = keywordQueryService.priceCode(priceCode);
		return jsonobject;
	}
	
	/**
	 * 动态模糊检索-材料分类
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @return
	 */
	@GetMapping(path = "/materialCtg")
	@ApiOperation(value="材料分类", notes="材料分类")
	public JSONObject materialCtgName() {
		
		JSONObject jsonobject = keywordQueryService.materialCtg();
		return jsonobject;
	}
	
	/**
	 * 模糊检索-供货地区
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/supplyRegion")
	@ApiOperation(value="供货地区", notes="供货地区")
	public JSONObject supplyRegion() {
		
		JSONObject jsonobject = keywordQueryService.supplyRegion();
		return jsonobject;
	}
	
	/**
	 * 模糊检索-区域
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/region")
	@ApiOperation(value="区域", notes="区域")
	public JSONObject region() {
		
		JSONObject jsonobject = keywordQueryService.region();
		return jsonobject;
	}
	
	/**
	 * 模糊检索-业务分类
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/businessCtg")
	@ApiOperation(value="业务分类", notes="业务分类")
	public JSONObject businessCtg() {
		
		JSONObject jsonobject = keywordQueryService.businessCtg();
		return jsonobject;
	}
	
	/**
	 * 模糊检索-城市
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @param userName
	 * @return
	 */
	@GetMapping(path = "/city")
	@ApiOperation(value="城市", notes="城市")
	public JSONObject city() {
		
		JSONObject jsonobject = keywordQueryService.city();
		return jsonobject;
	}
}
