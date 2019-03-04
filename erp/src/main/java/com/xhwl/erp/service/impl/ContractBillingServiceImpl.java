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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.domain.ContractAnalysisRepository;
import com.xhwl.erp.domain.ContractBillingRepository;
import com.xhwl.erp.domain.ContractInfoRepository;
import com.xhwl.erp.entity.ContractAnalysis;
import com.xhwl.erp.entity.ContractBilling;
import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.service.ContractBillingService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class ContractBillingServiceImpl implements ContractBillingService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractBillingServiceImpl.class);
	
	@Autowired
	private ContractBillingRepository contractBillingRepository;
	
	@Autowired
	private ContractInfoRepository contractInfoRepository;
	
	@Autowired
	private ContractAnalysisRepository contractAnalysisRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(contractBillingRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findAllByContractInfoAndPage(Long ci_id,Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		float totalAmount = 0;
		
		//获得合同信息
		ContractInfo contractInfo = contractInfoRepository.findOne(ci_id);
		//获得分页下的合同开票信息
		Page<ContractBilling> contractBillingList =  contractBillingRepository.findByContractInfo(contractInfo,pageable);
		//统计 累计开票金额
		List<ContractBilling> result =  contractBillingRepository.findByContractInfo(contractInfo);
		for (ContractBilling contractBilling : result) {
			totalAmount += contractBilling.getAmount();
		}
		
		resultMap.put("contractBillingList", contractBillingList);
		resultMap.put("totalAmount", totalAmount);
		
		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ContractBilling contractBilling = contractBillingRepository.findOne(id);
		if(contractBilling == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("contractBilling", contractBilling);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(ContractBilling contractBilling) {
		ResultJson resultJson = new ResultJson();
		
		/**
		 * 设置合同信息外键
		 */
		if(contractBilling.getContractInfo()!=null) {
			ContractInfo contractInfo = contractInfoRepository.findOne(contractBilling.getContractInfo().getId());
			if(contractInfo == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			setDiffAmount(contractInfo,contractBilling.getDiffAmount());//统计已开票金额
			
			contractBilling.setContractInfo(contractInfo);
		}
		
		//保存
		contractBillingRepository.save(contractBilling);

		resultJson.setSuccess(true);		
		resultJson.setData(contractBilling);	
		LOGGER.info("保存实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			ContractBilling contractBilling = contractBillingRepository.findOne(ids[i]);
			if(contractBilling == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			setDiffAmount(contractBilling.getContractInfo(),-contractBilling.getAmount());//减去已开票金额
			contractBillingRepository.delete(contractBilling);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public ResultJson search(String name,String number,Date date,Date date1,String contractInfo_name,String contractInfo_code, Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		
		Page<ContractBilling> contractBillingList = contractBillingRepository.findAll(new Specification<ContractBilling>(){
			@Override
			public Predicate toPredicate(Root<ContractBilling> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 
                if(null != name){
                    predicates.add(cb.like(root.get("name"), "%"+name+"%"));
                }
                if(null != number){
                    predicates.add(cb.like(root.get("number"), "%"+number+"%"));
                }
                if(null != date){
                    predicates.add(cb.between(root.get("date"), date, date1));
                }
                if(null != contractInfo_name){
                		predicates.add(cb.like(root.get("contractInfo").get("name").as(String.class), "%"+contractInfo_name+"%"));
                }
                if(null != contractInfo_code){
	            		predicates.add(cb.like(root.get("contractInfo").get("code").as(String.class), "%"+contractInfo_code+"%"));
	            }
				
			    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(contractBillingList);
		LOGGER.info("数据查询");
		return resultJson;
	}
	
	@Override
	public ResultJson importData(JSONArray contractBillingArrary) {
		ResultJson resultJson = new ResultJson();
		//循环保存
		for (int i = 0; i < contractBillingArrary.size(); i++) {
			JSONObject obj = contractBillingArrary.getJSONObject(i);
			String ci_code = obj.getString("ci_code");//合同编号
			ContractInfo contractInfo = contractInfoRepository.findByCode(ci_code);
			if(contractInfo == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			
			ContractBilling contractBilling = new ContractBilling();
			contractBilling.setName(obj.getString("name"));//开票抬头名称
			contractBilling.setAmount(obj.getFloatValue("amount"));//开票金额
			contractBilling.setDiffAmount(obj.getFloatValue("diffAmount"));//开票差额
			contractBilling.setDate(obj.getDate("date"));//开票日期
			contractBilling.setNumber(obj.getString("number"));//发票号码
			contractBilling.setContent(obj.getString("content"));//开票内容
			contractBilling.setTaxRate( obj.getString("taxRate"));//税率
			contractBilling.setTax(obj.getString("tax"));//税金
			contractBilling.setIncome(obj.getString("income"));//收入不含税
			contractBilling.setContractInfo(contractInfo);//合同
			setDiffAmount(contractInfo,obj.getFloatValue("diffAmount"));//统计已开票金额
			contractBillingRepository.save(contractBilling);
		}
		
		resultJson.setSuccess(true);		
		resultJson.setData("数据已导入");
		LOGGER.info("创建实体");
		return resultJson;
	}
	
	/**
	 * 统计已开票金额
	 * @author jiayiwu
	 * @date 2018年4月17日
	 * @param contractInfo
	 * @param diffAmount
	 */
	public void setDiffAmount(ContractInfo contractInfo,double diffAmount) {
		/**
		 * 设置合同基础信息中的已开票金额
		 */
		double invoicedAmount = contractInfo.getInvoicedAmount()+diffAmount;//统计已开票金额
		contractInfo.setInvoicedAmount(invoicedAmount);
		contractInfoRepository.save(contractInfo);
		
		/**
		 * 设置项目进度分析中的累计开票金额
		 */
		ContractAnalysis contractAnalysis = contractAnalysisRepository.findByContractInfo(contractInfo);
		contractAnalysis.setTotalBillingAmount(invoicedAmount);
		contractAnalysisRepository.save(contractAnalysis);
	}
}
