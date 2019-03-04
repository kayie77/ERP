package com.xhwl.erp.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Bussiness业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface KeywordQueryService {
	
	/**
	 * 客户名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @return
	 */
	JSONObject clientName(String clientName);
	
	/**
	 * 用户名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param clientName
	 * @return
	 */
	JSONObject userName(String userName);
	
	/**
	 * 商机名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param role_code
	 * @param businessName
	 * @return
	 */
	JSONObject businessName(String role_code,String businessName);
	
	/**
	 * 商机编码
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param role_code
	 * @param businessCode
	 * @return
	 */
	JSONObject businessCode(String role_code,String businessCode);
	
	/**
	 * 合同名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param role_code
	 * @param contractInfoName
	 * @return
	 */
	JSONObject contractInfoName(String role_code,String contractInfoName);
	
	/**
	 * 合同编码
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param role_code
	 * @param contractInfoCode
	 * @return
	 */
	JSONObject contractInfoCode(String role_code,String contractInfoCode);
	
	/**
	 * 开票号码
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param role_code
	 * @param number
	 * @return
	 */
	JSONObject contractBillingNumber(String role_code,String number);
	
	/**
	 * 付款合同编码
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param role_code
	 * @param paymentContractCode
	 * @return
	 */
	JSONObject paymentContractCode(String role_code,String paymentContractCode);
	
	/**
	 * 订单编号
	 * @author jiayiwu
	 * @date 2018年4月26日
	 * @param role_code
	 * @param orderCode
	 * @return
	 */
	JSONObject orderCode(String role_code,String orderCode);
	
	/**
	 * 入库单号
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param role_code
	 * @param inboundListCode
	 * @return
	 */
	JSONObject inboundListCode(String role_code,String inboundListCode);
	
	/**
	 * 出库单号
	 * @author jiayiwu
	 * @date 2018年4月28日
	 * @param role_code
	 * @param outboundListCode
	 * @return
	 */
	JSONObject outboundListCode(String role_code,String outboundListCode);
	
	/**
	 * 项目名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param role_code
	 * @param inboundListCode
	 * @return
	 */
	JSONObject projectName(String role_code,String projectName);
	
	/**
	 * 供应商名称
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @param role_code
	 * @param inboundListCode
	 * @return
	 */
	JSONObject supplyName(String supplyName);
	
	/**
	 * 产品名称
	 * @author jiayiwu
	 * @date 2018年4月25日
	 * @param priceName
	 * @return
	 */
	JSONObject priceName(String priceName);
	
	/**
	 * 产品编码
	 * @author jiayiwu
	 * @date 2018年4月25日
	 * @param priceCode
	 * @return
	 */
	JSONObject priceCode(String priceCode);
	
	/**
	 * 材料分类
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @return
	 */
	JSONObject materialCtg();
	
	/**
	 * 供货地区
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @return
	 */
	JSONObject supplyRegion();
	
	/**
	 * 区域
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @return
	 */
	JSONObject region();
	
	/**
	 * 业务分类
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @return
	 */
	JSONObject businessCtg();
	
	/**
	 * 城市 
	 * @author jiayiwu
	 * @date 2018年4月23日
	 * @return
	 */
	JSONObject city();
	
}
