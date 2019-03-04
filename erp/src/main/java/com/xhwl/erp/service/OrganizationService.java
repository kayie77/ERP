package com.xhwl.erp.service;

import java.util.List;

import com.xhwl.erp.util.tree.ETreeNode;

/**
 * OrganizationService 业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface OrganizationService {
	
	/**
	 * 返回组织架构列表-树形结构
	 * @author jiayiwu
	 * @date 2018年2月1日
	 * @return
	 */
	List<ETreeNode> tree();

}
