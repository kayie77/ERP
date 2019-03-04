package com.xhwl.erp.service;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.OutboundList;
import com.xhwl.erp.util.result.ResultJson;

/**
 * 出库清单业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface OutboundListService {
	
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
	 * @param outboundList
	 * @return
	 */
	ResultJson save(OutboundList outboundList);
	
	/**
	 * 删除数据ß
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年4月25日
	 * @param role_code
	 * @param inboundList_name
	 * @param code
	 * @param region_id
	 * @param project_name
	 * @param date
	 * @param date1
	 * @param pageable
	 * @return
	 */
	ResultJson search(String role_code,String inboundList_name, String code,Long region_id,String contractInfo_name,Date date,Date date1,Pageable pageable);
	
}
