package com.xhwl.erp.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.OutboundDetaile;
import com.xhwl.erp.util.result.ResultJson;

/**
 * 出库明细业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface OutboundDetaileService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);
	
	/**
	 * 通过采购清单返回数据
	 * @author jiayiwu
	 * @date 2018年3月1日
	 * @param ol_id
	 * @param pageable
	 * @return
	 */
	ResultJson findByOutboundList(Long ol_id,Pageable pageable);
	
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
	ResultJson save(List<OutboundDetaile > outboundDetaileArrary);
	
	/**
	 * 删除数据ß
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);
}
