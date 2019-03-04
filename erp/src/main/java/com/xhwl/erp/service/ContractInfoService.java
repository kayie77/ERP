package com.xhwl.erp.service;

import java.util.Date;
import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.util.result.ResultJson;

/**
 * ContractInfoService业务接口
 * @author jiayiwu
 * @date 2018年1月15日
 */
public interface ContractInfoService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);
	
	/**
	 * 获取更新时所需数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param id
	 * @return
	 */
	ResultJson findUpdateData(Long id);
	
	/**
	 * 根据商机获取数据
	 * @author jiayiwu
	 * @date 2018年2月8日
	 * @param b_id
	 * @return
	 */
	ResultJson findAllByBussiness(Long b_id);
	
	/**
	 * 保存数据
	 * @author jiayiwu
	 * @date 2018年1月8日
	 * @param contractInfo
	 * @return
	 */
	ResultJson save(ContractInfo contractInfo);
	
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
	 * @param ci_id
	 * @param date
	 * @param signDate
	 * @param region_id
	 * @param bctg_id
	 * @param isExecute
	 * @param pageable
	 * @return
	 */
	ResultJson search(String name,String code,Date term,Date signDate,Long region_id,String businessCtg_name, Pageable pageable);

}
