package com.xhwl.erp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.OrganizationRepository;
import com.xhwl.erp.domain.UserRepository;
import com.xhwl.erp.entity.Organization;
import com.xhwl.erp.service.UserService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(userRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findByOrganization(Long o_id,Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		Organization organization = organizationRepository.findOne(o_id);
		if(organization == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultJson.setData(userRepository.findByOrganization(organization,pageable));
		resultJson.setSuccess(true);
		return resultJson;
	}

}
