package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.InboundDetaile;
import com.xhwl.erp.util.result.ResultJson;

/**
 *  入库明细业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface InboundDetaileService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);
	
	/**
	 * 通过付款合同单返回数据
	 * @author jiayiwu
	 * @date 2018年3月1日
	 * @param pc_id
	 * @return
	 */
    ResultJson findAllByPaymentContract(Long pc_id);
	
	/**
	 * 保存数据实体
	 * @author jiayiwu
	 * @date 2018年1月30日
	 * @param inboundDetaile
	 * @return
	 */
	ResultJson save(InboundDetaile inboundDetaile);
	
}
