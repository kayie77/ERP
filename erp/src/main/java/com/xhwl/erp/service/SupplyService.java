package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.Supply;
import com.xhwl.erp.util.result.ResultJson;

/**
 * 供应商业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface SupplyService {
	
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
	 * 保存数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param business
	 * @return
	 */
	ResultJson save(Supply supply);
	
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
	 * @date 2018年3月22日
	 * @param name
	 * @param materialCategory
	 * @param enterpriseNature
	 * @param cooperativeType
	 * @param category
	 * @param grade
	 * @param type
	 * @param supplyCycle
	 * @param reviewState
	 * @param settlementMethod
	 * @param region_id
	 * @param pageable
	 * @return
	 */

	ResultJson search(String name,String materialCategory,String enterpriseNature,String cooperativeType,String category,
			String grade,String type,String supplyCycle,String reviewState,String settlementMethod,Long region_id,Pageable pageable);

}
