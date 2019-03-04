package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.Billing;
import com.xhwl.erp.util.result.ResultJson;

/**
 * ContractInfoService业务接口
 * @author jiayiwu
 * @date 2018年1月15日
 */
public interface BillingService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);
	
	/**
	 * 根据付款合同获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param pc_id
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPaymentContractAndPage(Long pc_id,Pageable pageable);
	
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
	 * @param contractBilling
	 * @return
	 */
	ResultJson save(Billing billing);
	
	/**
	 * 删除数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);
	
}
