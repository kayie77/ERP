package com.xhwl.erp.service.impl;

import java.nio.file.Path;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.xhwl.erp.controller.FileUploadController;
import com.xhwl.erp.domain.ContractChangeRepository;
import com.xhwl.erp.domain.ContractInfoRepository;
import com.xhwl.erp.entity.ContractChange;
import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.service.ContractChangeService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.file.StorageService;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class ContractChangeServiceImpl implements ContractChangeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractChangeServiceImpl.class);
	
	@Autowired
	private  StorageService storageService;
	
	@Autowired
	private ContractChangeRepository contractChangeRepository;
	
	@Autowired
	private ContractInfoRepository contractInfoRepository;
	
	@Override
	public ResultJson findAllByContractInfo(Long ci_id,Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		ContractInfo contractInfo = contractInfoRepository.findOne(ci_id);
		resultJson.setData(contractChangeRepository.findByContractInfo(contractInfo, pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ContractChange contractChange = contractChangeRepository.findOne(id);
		if(contractChange == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("contractChange", contractChange);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(Long id,String describtion,String person,Long ci_id,MultipartFile file) {
		ResultJson resultJson = new ResultJson();
		ContractChange contractChange;//初始化实体
		
		/**
		 * 新增、编辑判断
		 */
		if(id==null) {
			contractChange = new ContractChange();
		}else {
			contractChange = contractChangeRepository.findOne(id);
			 if(contractChange == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
		}

		/**
		 * 保存合同基础信息外键
		 */
		if(ci_id!=null) {
			ContractInfo contractInfo = contractInfoRepository.findOne(ci_id);
			if(contractInfo == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			contractChange.setContractInfo(contractInfo);
		}
		contractChange.setPerson(person);//保存上传人
		if(describtion!=null) {//保存描述
			contractChange.setDescribtion(describtion);
		}
		contractChange.setDate(new Date());//保存上传时间
		contractChange.setFileName(file.getOriginalFilename());//保存上传文件名
		
		/**
		 * 保存文件
		 */
		String date=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()); 
		String ogFilename = file.getOriginalFilename();
		String filename = ogFilename.substring(0,ogFilename.lastIndexOf("."));
		String prefix=ogFilename.substring(ogFilename.lastIndexOf("."));
    		String NewFileName = filename + "_" + date + prefix; 
    		storageService.store(file,date);
        Path path = storageService.load(NewFileName);
        
        String url = MvcUriComponentsBuilder
        .fromMethodName(FileUploadController.class,"serveFile",path.getFileName().toString())
        .build().toString();
        contractChange.setUrl(url);
        
		/**
		 * 实体保存
		 */
        contractChangeRepository.save(contractChange);

		resultJson.setSuccess(true);		
		LOGGER.info("保存实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			ContractChange contractChange = contractChangeRepository.findOne(ids[i]);
			if(contractChange == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			contractChangeRepository.delete(contractChange);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
}
