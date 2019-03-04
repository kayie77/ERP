package com.xhwl.erp.service.impl;

import java.util.ArrayList;
import java.util.List;

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

import com.xhwl.erp.domain.ClientRepository;
import com.xhwl.erp.entity.Client;
import com.xhwl.erp.service.ClientService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class ClientServiceImpl implements ClientService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);
	
	@Autowired
	ClientRepository clientRepository;

	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(clientRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findAll() {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(clientRepository.findAll());
		resultJson.setSuccess(true);
		
		LOGGER.info("获取全部数据");
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		//获取对应id实体
		Client client = clientRepository.findOne(id);
		if(client == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultJson.setData(client);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(Client client) {
		
		ResultJson resultJson = new ResultJson();
		//保存客户实体
		clientRepository.save(client);
		
		resultJson.setSuccess(true);		
		resultJson.setData(client);
		LOGGER.info("创建实体：" + client.toString());
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除多个客户
		for( int i = 0 ; i < ids.length; i++) {
			Client client = clientRepository.findOne(ids[i]);
			if(client == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			clientRepository.delete(client);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public ResultJson search(String name,String category,String type,String person, Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		
		Page<Client> clientList = clientRepository.findAll(new Specification<Client>(){
			@Override
			public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 

				if(null != name){
	        			predicates.add(cb.like(root.get("name"), "%"+name+"%"));
	            }
				if(null != category){
	        			predicates.add(cb.like(root.get("category"), "%"+category+"%"));
	            }
				if(null != type){
	        			predicates.add(cb.like(root.get("type"), "%"+type+"%"));
	            }
				if(null != person){
	        			predicates.add(cb.like(root.get("person"), "%"+person+"%"));
	            }
				
			    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(clientList);
		LOGGER.info("数据查询");
		return resultJson;
	}
}
