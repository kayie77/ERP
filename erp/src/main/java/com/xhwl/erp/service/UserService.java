package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.util.result.ResultJson;

public interface UserService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);
	
	/**
	 * 通过组织架构返回用户信息
	 * @author jiayiwu
	 * @date 2018年4月18日
	 * @param o_id
	 * @param pageable
	 * @return
	 */
	ResultJson findByOrganization(Long o_id,Pageable pageable);

}
