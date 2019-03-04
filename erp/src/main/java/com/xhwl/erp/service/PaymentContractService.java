package com.xhwl.erp.service;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.entity.PaymentContract;
import com.xhwl.erp.util.result.ResultJson;

/**
 * 付款合同业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface PaymentContractService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(String role_code,Pageable pageable);
	
	/**
	 * 获取统计金额
	 * @author jiayiwu
	 * @date 2018年5月17日
	 * @param role_code
	 * @return
	 */
	JSONObject getTotalAmount(String role_code);
	
	/**
	 * 获取更新时所需数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param id
	 * @return
	 */
	ResultJson findUpdateData(Long id);
	
	/**
	 * 保存数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param paymentContract
	 * @return
	 */
	ResultJson save(PaymentContract paymentContract);
	
	/**
	 * 删除数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年3月19日
	 * @param orderCode
	 * @param code
	 * @param project
	 * @param department
	 * @param b_ctg_id
	 * @param m_ctg_id
	 * @param deliveryStatus
	 * @param contractInfo_id
	 * @param applicationTime
	 * @param applicationTime1
	 * @param amount
	 * @param amount1
	 * @param pageable
	 * @return
	 */
	ResultJson search(String role_code,String orderCode,String code,String project,String department,String b_ctg_name,String m_ctg_name,
			String deliveryStatus,String contractInfo_code,String contractInfo_name,Date applicationTime,Date applicationTime1,double adAmount,double adAmount1,double acAmount,double acAmount1,Pageable pageable);
}
