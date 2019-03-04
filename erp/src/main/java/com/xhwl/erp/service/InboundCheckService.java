package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.InboundCheck;
import com.xhwl.erp.util.result.ResultJson;

/**
 *  入库明细业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface InboundCheckService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);
	
	/**
	 * 根据出库单获取数据
	 * @author jiayiwu
	 * @date 2018年3月1日
	 * @param pc_id
	 * @return
	 */
	ResultJson findByInboundList(Long il_id);
	
	/**
	 * 提交审核
	 * @author jiayiwu
	 * @date 2018年1月30日
	 * @param inboundCheck
	 * @return
	 */
	ResultJson submit(InboundCheck inboundCheck);
	
	/**
	 * 办事处经理审核
	 * @author jiayiwu
	 * @date 2018年4月28日
	 * @param inboundCheck
	 * @return
	 */
	ResultJson officeCheck(InboundCheck inboundCheck);
	
	/**
	 * 本部-成本部审核
	 * @author jiayiwu
	 * @date 2018年4月28日
	 * @param inboundCheck
	 * @return
	 */
	ResultJson costCheck(InboundCheck inboundCheck);
}
