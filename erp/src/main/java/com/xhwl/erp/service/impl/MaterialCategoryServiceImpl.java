package com.xhwl.erp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.MaterialCategoryRepository;
import com.xhwl.erp.service.MaterialCategoryService;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class MaterialCategoryServiceImpl implements MaterialCategoryService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MaterialCategoryServiceImpl.class);
	
	@Autowired
	MaterialCategoryRepository materialCategoryRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(materialCategoryRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}

}
