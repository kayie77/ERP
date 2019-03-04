package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.ContractBasis;
import com.xhwl.erp.util.result.ResultJson;

/**
 * ContractInfoService业务接口
 * @author jiayiwu
 * @date 2018年1月15日
 */
public interface ContractBasisService {
	
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
	ResultJson findAllByContractInfoAndPage(Long ci_id,Pageable pageable);
	
	/**
	 * 获取新增实体时所需数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @return
	 */
	ResultJson findInsertData();
	
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
	 * @param contractBasis
	 * @return
	 */
	ResultJson save(ContractBasis contractBasis);
	
	/**
	 * 删除数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);

}
