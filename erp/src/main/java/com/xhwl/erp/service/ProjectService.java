package com.xhwl.erp.service;

import java.util.Date;
import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.Project;
import com.xhwl.erp.util.result.ResultJson;

/**
 * ProjectService 业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface ProjectService {

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
	 * @param project
	 * @param pdList
	 * @return
	 */
	ResultJson save(Project project);//List<ProjectDesign> pdList
	
	/**
	 * 删除数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年3月29日
	 * @param name
	 * @param firstEntry
	 * @param firstEntry1
	 * @param archFormat
	 * @param communityType
	 * @param contractMode
	 * @param region_id
	 * @param city_id
	 * @param client_id
	 * @param pageable
	 * @return
	 */
	ResultJson search(String role_code,String name,Date firstEntry,Date firstEntry1,String archFormat,String communityType,String contractMode,Long region_id,Long city_id,String client_name, Pageable pageable);

}
