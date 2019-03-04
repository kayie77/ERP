package com.xhwl.erp.service.impl;

import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.domain.ContractAnalysisRepository;
import com.xhwl.erp.domain.ContractBillingRepository;
import com.xhwl.erp.domain.ContractInfoRepository;
import com.xhwl.erp.domain.ContractReceivedRepository;
import com.xhwl.erp.entity.ContractAnalysis;
import com.xhwl.erp.entity.ContractBilling;
import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.entity.ContractReceived;
import com.xhwl.erp.service.ContractReceivedService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class ContractReceivedServiceImpl implements ContractReceivedService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractReceivedServiceImpl.class);
	
	@Autowired
	private ContractReceivedRepository contractReceivedRepository;
	
	@Autowired
	private ContractBillingRepository contractBillingRepository;
	
	@Autowired
	private ContractInfoRepository contractInfoRepository;
	
	@Autowired
	private ContractAnalysisRepository contractAnalysisRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(contractReceivedRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findAllByContractInfo(Long ci_id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		float totalAmount = 0;
		
		//获得合同信息
		ContractInfo contractInfo = contractInfoRepository.findOne(ci_id);
		//获得合同开票信息数组
		List<ContractBilling> contractBillingList = contractBillingRepository.findByContractInfo(contractInfo);
		for (ContractBilling contractBilling : contractBillingList) {
			List<ContractReceived>  contractReceivedList = contractReceivedRepository.findByContractBilling(contractBilling);
			resultMap.put("contractReceivedList", contractReceivedList);
			//统计 累计回款金额
			for (ContractReceived result : contractReceivedList) {
				totalAmount += result.getAmount();
			}
		}
		resultMap.put("totalAmount", totalAmount);
		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 查询全部数据：");
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ContractReceived contractReceived = contractReceivedRepository.findOne(id);
		if(contractReceived == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("contractReceived", contractReceived);
		
		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(ContractReceived contractReceived) {
		ResultJson resultJson = new ResultJson();
		
		/**
		 * 设置开票信息外键
		 */
		if(contractReceived.getContractBilling()!=null) {
			ContractBilling contractBilling = contractBillingRepository.findOne(contractReceived.getContractBilling().getId());
			if(contractBilling == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			
			ContractInfo contractInfo = contractInfoRepository.findOne(contractBilling.getContractInfo().getId());
			if(contractInfo == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			setDiffAmount(contractInfo,contractReceived.getDiffAmount());//统计已回款金额
			
			contractReceived.setContractBilling(contractBilling);
		}
		
		//保存
		contractReceivedRepository.save(contractReceived);

		resultJson.setSuccess(true);		
		resultJson.setData(contractReceived);	
		LOGGER.info("保存实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			ContractReceived contractReceived = contractReceivedRepository.findOne(ids[i]);
			if(contractReceived == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			setDiffAmount(contractReceived.getContractBilling().getContractInfo(),-contractReceived.getAmount());//减去已回款金额
			contractReceivedRepository.delete(contractReceived);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public ResultJson search(String contractInfo_name,String contractInfo_code, Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		
		Page<ContractReceived> contractReceivedList = contractReceivedRepository.findAll(new Specification<ContractReceived>(){
			@Override
			public Predicate toPredicate(Root<ContractReceived> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>();
				
				if(null != contractInfo_name){
	            		predicates.add(cb.like(root.get("contractBilling").get("contractInfo").get("name").as(String.class), "%"+contractInfo_name+"%"));
	            }
	            if(null != contractInfo_code){
	            		predicates.add(cb.like(root.get("contractBilling").get("contractInfo").get("code").as(String.class), "%"+contractInfo_code+"%"));
	            }
			
                	return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(contractReceivedList);
		LOGGER.info("数据查询");
		return resultJson;
	}
	
	@Override
	public ResultJson importData(JSONArray contractReceivedArrary) {
		ResultJson resultJson = new ResultJson();
		//循环保存
		for (int i = 0; i < contractReceivedArrary.size(); i++) {
			JSONObject obj = contractReceivedArrary.getJSONObject(i);
			String cb_number = obj.getString("cb_number");
			ContractBilling contractBilling = contractBillingRepository.findByNumber(cb_number);
			if(contractBilling == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());

			ContractInfo contractInfo = contractInfoRepository.findOne(contractBilling.getContractInfo().getId());
			if(contractInfo == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			
			ContractReceived contractReceived = new ContractReceived();
			contractReceived.setAmount(obj.getFloatValue("amount"));//回款金额
			contractReceived.setDiffAmount(obj.getFloatValue("diffAmount"));//回款差额
			contractReceived.setDate(obj.getDate("date"));//回款金额
			contractReceived.setContractBilling(contractBilling);//发票
			setDiffAmount(contractInfo,obj.getFloatValue("diffAmount"));//统计已回款金额
	
			contractReceivedRepository.save(contractReceived);
		}
		
		resultJson.setSuccess(true);		
		resultJson.setData("数据已导入");
		LOGGER.info("创建实体");
		return resultJson;
	}
	
	/**
	 * 统计已回款金额
	 * @author jiayiwu
	 * @date 2018年4月17日
	 * @param contractInfo
	 * @param diffAmount
	 */
	public void setDiffAmount(ContractInfo contractInfo,double diffAmount) {
		/**
		 * 设置合同基础信息已回款金额
		 */
		double receivedAmount = contractInfo.getReceivedAmount()+diffAmount;
		contractInfo.setReceivedAmount(receivedAmount);
		contractInfoRepository.save(contractInfo);
		
		/**
		 * 设置项目进度分析中的累计回款金额
		 */
		ContractAnalysis contractAnalysis = contractAnalysisRepository.findByContractInfo(contractInfo);
		contractAnalysis.setTotalReceivedAmount(receivedAmount);
		contractAnalysisRepository.save(contractAnalysis);
	}
}
