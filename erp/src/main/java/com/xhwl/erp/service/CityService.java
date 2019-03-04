package com.xhwl.erp.service;

import java.util.List;

import com.xhwl.erp.util.tree.ETreeNode;

/**
 * CityService业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface CityService {

	/**
	 * 返回城市列表-树形结构
	 * @author jiayiwu
	 * @date 2018年1月10日
	 * @return
	 */
	List<ETreeNode> tree();
}
