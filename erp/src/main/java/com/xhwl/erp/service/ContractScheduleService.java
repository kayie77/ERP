package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.ContractSchedule;
import com.xhwl.erp.util.result.ResultJson;

/**
 * ContractReceivedService业务接口
 * @author jiayiwu
 * @date 2018年1月15日
 */
public interface ContractScheduleService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);
	
	/**
	 * 根据合同信息获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param ci_id
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByContractInfo(Long ci_id);
	
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
	ResultJson save(ContractSchedule contractSchedule);
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年4月2日
	 * @param stage
	 * @param projectStatus
	 * @param receivedStatus
	 * @param materialStatus
	 * @param artificialStatus
	 * @param comprehensiveStatus
	 * @param paymentBalanceStatus
	 * @param cashBalanceStatus
	 * @param contractInfo_id
	 * @param pageable
	 * @return
	 */
	ResultJson search(String stage,String projectStatus,String receivedStatus,String materialStatus, String artificialStatus,
			String comprehensiveStatus,String paymentBalanceStatus,String cashBalanceStatus,String contractInfo_name,String contractInfo_code, Pageable pageable);

}
