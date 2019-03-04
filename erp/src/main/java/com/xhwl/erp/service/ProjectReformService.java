package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.ProjectReform;
import com.xhwl.erp.util.result.ResultJson;

/**
 * ProjectReformService 业务接口
 * @author jiayiwu
 * @date 2018年11月19日
 */
public interface ProjectReformService {
	
	/**
	 * 通过项目返回数据
	 * @author jiayiwu
	 * @date 2018年3月1日
	 * @param p_id
	 * @return
	 */
	ResultJson findByProject(Long p_id);
	
	/**
	 * 获取更新时所需数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param id
	 * @return
	 */
	ResultJson findUpdateData(Long id);
	
	/**
	 * 获取单个年份多项目的改造信息
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param id
	 * @return
	 */
	ResultJson findDataByYear(String year,Pageable pageable);
	
	/**
	 * 保存数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param p_id
	 * @param projectReform
	 * @return
	 */
	ResultJson save(Long p_id,ProjectReform projectReform);
	
	/**
	 * 删除数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);
	
}
