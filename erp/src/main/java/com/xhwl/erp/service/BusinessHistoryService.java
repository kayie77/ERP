package com.xhwl.erp.service;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.entity.BusinessHistory;
import com.xhwl.erp.util.result.ResultJson;

/**
 * 价格体系业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface BusinessHistoryService {

	/**
	 * 根据价格体系获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年3月29日
	 * @param p_id
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByBusiness(Long b_id,Pageable pageable);
	
	/**
	 * 保存数据
	 * @author jiayiwu
	 * @date 2018年1月31日
	 * @param price
	 * @param session
	 * @return
	 */
	BusinessHistory save(JSONObject jsonObject);
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年4月10日
	 * @param b_id
	 * @param content
	 * @param time
	 * @param time1
	 * @param pageable
	 * @return
	 */
	ResultJson search(Long b_id,String content,Date time,Date time1,Pageable pageable);

}
