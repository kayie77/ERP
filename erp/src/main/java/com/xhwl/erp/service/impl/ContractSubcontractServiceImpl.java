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
import com.xhwl.erp.domain.ContractInfoRepository;
import com.xhwl.erp.domain.ContractSubcontractRepository;
import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.entity.ContractSubcontract;
import com.xhwl.erp.service.ContractSubcontractService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.file.StorageService;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class ContractSubcontractServiceImpl implements ContractSubcontractService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractSubcontractServiceImpl.class);
	
	@Autowired
	private  StorageService storageService;
	
	@Autowired
	private ContractSubcontractRepository contractSubcontractRepository;
	
	@Autowired
	private ContractInfoRepository contractInfoRepository;
	
	@Override
	public ResultJson findAllByContractInfo(Long ci_id,Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		ContractInfo contractInfo = contractInfoRepository.findOne(ci_id);
		resultJson.setData(contractSubcontractRepository.findByContractInfo(contractInfo, pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ContractSubcontract contractSubcontract = contractSubcontractRepository.findOne(id);
		if(contractSubcontract == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("contractSubcontract", contractSubcontract);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(Long id,String describtion,String person,Long ci_id,MultipartFile file) {
		ResultJson resultJson = new ResultJson();
		ContractSubcontract contractSubcontract;//初始化实体
		
		/**
		 * 新增、编辑判断
		 */
		if(id==null) {
			 contractSubcontract = new ContractSubcontract();
		}else {
			 contractSubcontract = contractSubcontractRepository.findOne(id);
			 if(contractSubcontract == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		}

		/**
		 * 保存所有关键信息
		 */
		if(ci_id!=null) {//保存合同基础信息外键
			ContractInfo contractInfo = contractInfoRepository.findOne(ci_id);
			if(contractInfo == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			contractSubcontract.setContractInfo(contractInfo);
		}
		contractSubcontract.setPerson(person);//保存上传人
		if(describtion!=null) {//保存描述
			contractSubcontract.setDescribtion(describtion);
		}
		contractSubcontract.setDate(new Date());//保存上传时间
		contractSubcontract.setFileName(file.getOriginalFilename());//保存上传文件名称
		
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
        contractSubcontract.setUrl(url);//保存上传文件地址。用于下载文件
        
		/**
		 * 实体保存
		 */
		contractSubcontractRepository.save(contractSubcontract);

		resultJson.setSuccess(true);		
		LOGGER.info("保存实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			ContractSubcontract contractSubcontract = contractSubcontractRepository.findOne(ids[i]);
			if(contractSubcontract == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			contractSubcontractRepository.delete(contractSubcontract);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
}
