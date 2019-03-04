package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.TenderOffer;
import com.xhwl.erp.util.result.ResultJson;

/**
 * Bussiness业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface TenderOfferService {
	
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
	 * @param tenderOffer
	 * @return
	 */
	ResultJson save(TenderOffer tenderOffer);
	
	/**
	 * 删除数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);
	
	/**
	 * 状态设置
	 * @author jiayiwu
	 * @date 2018年3月16日
	 * @param id
	 * @return
	 */
	ResultJson status(Long id);
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年3月19日
	 * @param business_id
	 * @param pageable
	 * @return
	 */
	ResultJson search(String role_code,String b_name,String b_code,Pageable pageable);
}
