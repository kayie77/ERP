package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.PaymentPlan;
import com.xhwl.erp.util.result.ResultJson;

/**
 * PaymentPlanService业务接口
 * @author jiayiwu
 * @date 2018年1月15日
 */
public interface PaymentPlanService {
	
	/**
	 * 根据合同交底获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param pc_id
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByContractBasisAndPage(Long cb_id,Pageable pageable);
	
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
	 * @date 2018年1月8日
	 * @param payment
	 * @return
	 */
	ResultJson save(PaymentPlan paymentPlan);
	
	/**
	 * 删除数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);
	
}
