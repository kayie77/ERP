package com.xhwl.erp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.ContractAnalysisRepository;
import com.xhwl.erp.domain.ContractBasisRepository;
import com.xhwl.erp.domain.ContractInfoRepository;
import com.xhwl.erp.entity.ContractAnalysis;
import com.xhwl.erp.entity.ContractBasis;
import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.service.ContractBasisService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class ContractBasisServiceImpl implements ContractBasisService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractBasisServiceImpl.class);
	
	@Autowired
	private ContractBasisRepository contractBasisRepository;
	
	@Autowired
	private ContractInfoRepository contractInfoRepository;
	
	@Autowired
	private ContractAnalysisRepository contractAnalysisRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(contractBasisRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findAllByContractInfoAndPage(Long ci_id,Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		ContractInfo contractInfo = contractInfoRepository.findOne(ci_id);
		resultJson.setData(contractBasisRepository.findByContractInfo(contractInfo, pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findInsertData() {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Iterable<ContractInfo> contractInfoList = contractInfoRepository.findAll();
		resultMap.put("contractInfoList", contractInfoList);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取新增实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ContractBasis contractBasis = contractBasisRepository.findOne(id);
		if(contractBasis == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("contractBasis", contractBasis);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(ContractBasis contractBasis) {
		ResultJson resultJson = new ResultJson();
		
		/**
		 * 手动set关联实体
		 */
		if(contractBasis.getContractInfo()!=null) {
			ContractInfo contractInfo = contractInfoRepository.findOne(contractBasis.getContractInfo().getId());
			if(contractInfo == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			contractBasis.setContractInfo(contractInfo);
			
			/**
			 * 设置项目进度分析中的所有交底金额
			 */
			ContractAnalysis contractAnalysis = contractAnalysisRepository.findByContractInfo(contractInfo);
			if(contractAnalysis == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			contractAnalysis.setBasisMaterialCost(contractBasis.getMaterialCost());
			contractAnalysis.setBasisArtificialCost(contractBasis.getArtificialCost());
			contractAnalysis.setBasisComprehensiveCost(contractBasis.getComprehensiveCost());
			contractAnalysisRepository.save(contractAnalysis);
		}
		
		//保存
		contractBasisRepository.save(contractBasis);

		resultJson.setSuccess(true);		
		resultJson.setData(contractBasis);	
		LOGGER.info("保存实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			ContractBasis contractBasis = contractBasisRepository.findOne(ids[i]);
			if(contractBasis == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			contractBasisRepository.delete(contractBasis);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
}
