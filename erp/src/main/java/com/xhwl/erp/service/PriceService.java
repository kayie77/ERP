package com.xhwl.erp.service;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.Price;
import com.xhwl.erp.util.result.ResultJson;

/**
 * 价格体系业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface PriceService {
	
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
	 * @date 2018年1月31日
	 * @param price
	 * @param session
	 * @return
	 */
	ResultJson save(Price price);
	
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
	 * @date 2018年4月11日
	 * @param p_id
	 * @param type
	 * @param system
	 * @param specModel
	 * @param brand
	 * @param startDate
	 * @param endDate
	 * @param supply_id
	 * @param pageable
	 * @return
	 */
	ResultJson search(String name,String code, String type, String system, String specModel, String brand,Date startDate,Date endDate, String supply_name,Pageable pageable);

}
