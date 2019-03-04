package com.xhwl.erp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.AccountRepository;
import com.xhwl.erp.domain.RoleRepository;
import com.xhwl.erp.entity.Account;
import com.xhwl.erp.entity.Role;
import com.xhwl.erp.service.AccountService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.MD5Util;

@Service
public class AccountServiceImpl implements AccountService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
    AccountRepository accountRepository;
	
	@Autowired
    RoleRepository roleRepository;
	
	@Override
	public Account login(String name,String password){
		/**
		 * 判断账户是否存在
		 */
		Account account = accountRepository.findByNameAndPassword(name, password);
		if(account == null) throw new AppWebException(ErrorConstant.ACCOUNT_NOTEXIT.getCode(), ErrorConstant.ACCOUNT_NOTEXIT.getMsg());
		
		/**
		 * 写入登录时间
		 */
		Date loginTime = new Date();
		account.setLoginTime(loginTime);
		
		LOGGER.info("--登录成功--");
		return account;
	}
	
	@Override
	public ResultJson register(Account account){
		ResultJson resultJson = new ResultJson();
		/**
		 * 判断用户是否已注册，用户名为唯一判断参数
		 */
		Account result = accountRepository.findByName(account.getName());
		if(result != null) throw new AppWebException(ErrorConstant.ACCOUNT_EXIT.getCode(), ErrorConstant.ACCOUNT_EXIT.getMsg());
		
		/**
		 * 写入管理员角色
		 */
		Role role = roleRepository.findByCode("admin");
		account.setRole(role);
		
		/**
		 * 写入创建时间,登录时间
		 */
		Date time = new Date();
		account.setSignTime(time);
		account.setLoginTime(time);
		
		/**
		 * 密码MD5加密
		 */
		account.setPassword(MD5Util.encode(account.getPassword()));
		/**
		 * 保存
		 */
		accountRepository.save(account);
		 	
		resultJson.setSuccess(true);
		resultJson.setData(account);
		LOGGER.info("--注册成功--");
		return resultJson;
	}
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(accountRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Account account = accountRepository.findOne(id);
		if(account == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		Iterable<Role> roleList = roleRepository.findAll();
		
		resultMap.put("account", account);
		resultMap.put("roleList", roleList);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(Account account) {
		ResultJson resultJson = new ResultJson();
		
		/**
		 * 设置合同信息外键
		 */
		if(account.getRole()!=null) {
			Role role = roleRepository.findOne(account.getRole().getId());
			if(role == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			account.setRole(role);
		}
		
		//保存
		accountRepository.save(account);

		resultJson.setSuccess(true);		
		resultJson.setData(account);	
		LOGGER.info("保存实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			Account account = accountRepository.findOne(ids[i]);
			if(account == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			accountRepository.delete(account);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public ResultJson search(String name, Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		
		Page<Account> accountList = accountRepository.findAll(new Specification<Account>(){
			@Override
			public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 

				if(null != name){
	        			predicates.add(cb.like(root.get("name"), "%"+name+"%"));
	            }
				
			    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(accountList);
		LOGGER.info("数据查询");
		return resultJson;
	}
	
    @Override
    public ResultJson password(Long id,String password) {
    		ResultJson resultJson = new ResultJson();
     	Account	account = accountRepository.findOne(id);
		account.setPassword(MD5Util.encode(password));//密码MD5加密
		account.setState(1);//设置状态
     	accountRepository.save(account);
     	resultJson.setSuccess(true);
     	return resultJson;
    }

}
