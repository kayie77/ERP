package com.xhwl.erp.service;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.InboundList;
import com.xhwl.erp.util.result.ResultJson;

/**
 * 付款合同业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface InboundListService {
	
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
	 * @param paymentContract
	 * @return
	 */
	ResultJson save(InboundList inboundList);
	
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
	 * @date 2018年4月2日
	 * @param orderCode
	 * @param code
	 * @param inputDate
	 * @param inputDate1
	 * @param supply_id    
	 * @param pageable
	 * @return
	 */
	ResultJson search(String role_code,String orderCode,String code,Date date,Date date1,String department,String supply_name,Pageable pageable);
}
