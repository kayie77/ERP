package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.Client;
import com.xhwl.erp.util.result.ResultJson;

/**
 * ClientService业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface ClientService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);
	
	/**
	 * 获取全部数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @return
	 */
	ResultJson findAll();
	
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
	 * @date 2018年1月8日
	 * @param client
	 * @return
	 */
	ResultJson save(Client client);
	
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
	 * @param category
	 * @param type
	 * @param person
	 * @param pageable
	 * @return
	 */
	ResultJson search(String name,String category,String type,String person, Pageable pageable);

}
