package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.Role;
import com.xhwl.erp.util.result.ResultJson;

/**
 * RoleService 业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface RoleService {
	
	/**
	 * 获取全部数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAll();
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);
	
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
	 * @param business
	 * @return
	 */
	ResultJson save(Role role);
	
	/**
	 * 删除数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);

}
