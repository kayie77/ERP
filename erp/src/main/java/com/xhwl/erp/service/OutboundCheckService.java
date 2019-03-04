package com.xhwl.erp.service;

import com.xhwl.erp.entity.OutboundCheck;
import com.xhwl.erp.util.result.ResultJson;

/**
 *  入库明细业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface OutboundCheckService {
	
	/**
	 * 根据出库清单获取数据
	 * @author jiayiwu
	 * @date 2018年3月1日
	 * @param pc_id
	 * @return
	 */
	ResultJson findByOutboundList(Long ol_id);
	
	/**
	 * 提交审核
	 * @author jiayiwu
	 * @date 2018年1月30日
	 * @param inboundCheck
	 * @return
	 */
	ResultJson submit(OutboundCheck outboundCheck);
	
	/**
	 * 办事处经理审核
	 * @author jiayiwu
	 * @date 2018年4月28日
	 * @param inboundCheck
	 * @return
	 */
	ResultJson officeCheck(OutboundCheck outboundCheck);
	
	/**
	 * 本部-成本部审核
	 * @author jiayiwu
	 * @date 2018年4月28日
	 * @param inboundCheck
	 * @return
	 */
	ResultJson costCheck(OutboundCheck outboundCheck);
	
}
