package com.xhwl.erp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.RegionRepository;
import com.xhwl.erp.entity.Region;
import com.xhwl.erp.service.RegionService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;


@Service
public class RegionServiceImpl implements  RegionService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegionServiceImpl.class);
	
	@Autowired
	RegionRepository regionRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(regionRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		//获取对应id实体
		Region region = regionRepository.findOne(id);
		if(region == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultJson.setData(region);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(Region region) {
		
		ResultJson resultJson = new ResultJson();
		
		resultJson.setSuccess(true);		
		resultJson.setData(regionRepository.save(region));//保存区域实体
		LOGGER.info("创建实体：" + region.toString());
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除多个区域
		for( int i = 0 ; i < ids.length; i++) {
			Region region = regionRepository.findOne(ids[i]);
			if(region == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			regionRepository.delete(region);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}

}
