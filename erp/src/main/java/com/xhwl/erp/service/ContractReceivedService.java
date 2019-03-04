package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.alibaba.fastjson.JSONArray;
import com.xhwl.erp.entity.ContractReceived;
import com.xhwl.erp.util.result.ResultJson;

/**
 * ContractReceivedService业务接口
 * @author jiayiwu
 * @date 2018年1月15日
 */
public interface ContractReceivedService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);
	
	/**
	 * 根据合同信息获取全部数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param ci_id
	 * @return
	 */
	ResultJson findAllByContractInfo(Long ci_id );
	
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
	ResultJson save(ContractReceived contractReceived);
	
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
	 * @date 2018年1月15日
	 * @param contractInfo_id
	 * @param pageable
	 * @return
	 */
	ResultJson  search(String contractInfo_name,String contractInfo_code, Pageable pageable);
	
	/**
	 * 数据导入
	 * @author jiayiwu
	 * @date 2018年4月16日
	 * @param contractReceivedArrary
	 * @return
	 */
	ResultJson importData(JSONArray contractReceivedArrary);

}
