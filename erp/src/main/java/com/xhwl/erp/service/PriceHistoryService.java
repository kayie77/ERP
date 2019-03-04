package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.entity.PriceHistory;
import com.xhwl.erp.util.result.ResultJson;

/**
 * 价格体系业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface PriceHistoryService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);

	/**
	 * 根据价格体系获取全部分页数据
	 * @author jiayiwu
	 * @date 2018年3月29日
	 * @param p_id
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPrice(Long p_id,Pageable pageable);
	
	/**
	 * 保存数据
	 * @author jiayiwu
	 * @date 2018年1月31日
	 * @param price
	 * @param session
	 * @return
	 */
	PriceHistory save(JSONObject jsonObject);

}
