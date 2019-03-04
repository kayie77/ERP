package com.xhwl.erp.service;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.Business;
import com.xhwl.erp.util.result.ResultJson;

/**
 * Bussiness业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface BusinessService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(String role_code,Pageable pageable);
	
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
	ResultJson save(Business business);
	
	/**
	 * 删除数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);
	
	/**
	 * 审核
	 * @author jiayiwu
	 * @date 2018年3月15日
	 * @param id
	 * @return
	 */
	ResultJson examine(Long id);
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年4月25日
	 * @param role_code
	 * @param name
	 * @param code
	 * @param sourcePerson
	 * @param startDate
	 * @param startDate1
	 * @param amount
	 * @param amount1
	 * @param executState
	 * @param examineState
	 * @param date
	 * @param date1
	 * @param city_id
	 * @param client_name
	 * @param client_ctg
	 * @param bussinesCtg_name
	 * @param bp_id
	 * @param region_id
	 * @param pageable
	 * @return
	 */

	ResultJson  search(String role_code,String name,String code,String sourcePerson,Date startDate,Date startDate1,double amount,double amount1,
			String executState,String examineState,Date date,Date date1,Long city_id,String client_name,String client_ctg,String bussinesCtg_name,Long bp_id,Long region_id,Pageable pageable);
	
}
