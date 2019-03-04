package com.xhwl.erp.service;

import org.springframework.data.domain.Pageable;

import com.xhwl.erp.entity.Account;
import com.xhwl.erp.util.result.ResultJson;

/**
 * account业务接口
 * @author jiayiwu
 * @date 2017年12月26日
 */
public interface AccountService {
	
	 /**
	  * 注册
	  * @author jiayiwu
	  * @date 2017年12月29日
	  * @param account
	  * @return
	  */
	 ResultJson register(Account account);
	 
	 /**
	  * 登录
	  * @author jiayiwu
	  * @date 2017年12月29日
	  * @param name
	  * @param password
	  * @return
	  */
	 Account login(String name,String password);
	 
	 /**
		 * 获取分页数据列表
		 * @author jiayiwu
		 * @date 2018年1月5日
		 * @param pageable
		 * @return
		 */
	ResultJson findAllByPage(Pageable pageable);
	
	/**
	 * 获取更新对象时所需数据
	 * @author jiayiwu
	 * @date 2018年4月20日
	 * @param id
	 * @return
	 */
	ResultJson findUpdateData(Long id);
	
	/**
	 * 保存对象
	 * @author jiayiwu
	 * @date 2018年4月20日
	 * @param account
	 * @return
	 */
	ResultJson save(Account account);
	
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
	 * @date 2018年3月31日
	 * @param name
	 * @param pageable
	 * @return
	 */
	ResultJson search(String name, Pageable pageable) ;
	
	
	/**
	 * 修改密码
	 * @author jiayiwu
	 * @date 2018年4月18日
	 * @param id
	 * @param password
	 * @return
	 */
	ResultJson password(Long id,String password);

}
