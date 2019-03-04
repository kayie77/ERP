package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.util.result.ResultJson;

/**
 * MaterialCategoryService 业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface MaterialCategoryService {

	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);

}
