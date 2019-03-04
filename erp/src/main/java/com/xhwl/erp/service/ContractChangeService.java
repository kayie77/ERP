package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.xhwl.erp.util.result.ResultJson;

/**
 * ContractInfoService业务接口
 * @author jiayiwu
 * @date 2018年1月15日
 */
public interface ContractChangeService {
	
	/**
	 * 根据合同信息获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param ci_id
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByContractInfo(Long ci_id,Pageable pageable);
	
	
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
	 * @date 2018年2月28日
	 * @param describtion
	 * @param file
	 * @param session
	 * @return
	 */
	ResultJson save(Long id,String describtion,String person,Long ci_id,MultipartFile file);
	
	/**
	 * 删除数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);

}
