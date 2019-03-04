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
import com.xhwl.erp.domain.ContractInfoRepository;
import com.xhwl.erp.domain.ContractPaymentRepository;
import com.xhwl.erp.entity.ContractAnalysis;
import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.entity.ContractPayment;
import com.xhwl.erp.service.ContractPaymentService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class ContractPaymentServiceImpl implements ContractPaymentService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractPaymentServiceImpl.class);
	
	@Autowired
	private ContractPaymentRepository contractPaymentRepository;
	
	@Autowired
	private ContractInfoRepository contractInfoRepository;
	
	@Autowired
	private ContractAnalysisRepository contractAnalysisRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(contractPaymentRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findAllByContractInfoAndPage(Long ci_id,Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//获得合同信息
		ContractInfo contractInfo = contractInfoRepository.findOne(ci_id);
		Page<ContractPayment> contractPaymentList =  contractPaymentRepository.findByContractInfo(contractInfo, pageable);
		//获得统计金额信息
		ContractAnalysis contractAnalysis = contractAnalysisRepository.findByContractInfo(contractInfo);
		if(contractAnalysis == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("contractPaymentList", contractPaymentList);
		resultMap.put("totalMaterialCost", contractAnalysis.getPayMaterialCost());
		resultMap.put("totalArtificialCost", contractAnalysis.getPayArtificialCost());
		resultMap.put("totalComprehensiveCost", contractAnalysis.getPayComprehensiveCost());
		resultMap.put("totalManageCost", contractAnalysis.getPayManageCost());
		resultMap.put("totalTax", contractAnalysis.getPayTaxCost());
		resultMap.put("totalProAmount", contractAnalysis.getPayTotalAmount());
		
		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ContractPayment contractPayment = contractPaymentRepository.findOne(id);
		if(contractPayment == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("contractPayment", contractPayment);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(ContractPayment contractPayment) {
		ResultJson resultJson = new ResultJson();
		
		/**
		 * 手动set关联实体
		 */
		if(contractPayment.getContractInfo()!=null) {
			ContractInfo contractInfo = contractInfoRepository.findOne(contractPayment.getContractInfo().getId());
			if(contractInfo == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			contractPayment.setContractInfo(contractInfo);
			
			setDiffAmount(contractInfo, contractPayment.getDiffMaterialCost(), contractPayment.getDiffArtificialCost(),
					contractPayment.getDiffComprehensiveCost(), contractPayment.getDiffManageCost(), contractPayment.getDiffTax());//统计所有付款累计金额
		}
		/**
		 * 设置投入金额（总额）
		 */
		double totalAmount = contractPayment.getMaterialCost()+contractPayment.getArtificialCost()+contractPayment.getComprehensiveCost()+contractPayment.getManageCost()+contractPayment.getTax();
		contractPayment.setTotalAmount(totalAmount);
		
		//保存
		contractPaymentRepository.save(contractPayment);

		resultJson.setSuccess(true);	
		resultJson.setData(contractPayment);	
		LOGGER.info("保存实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			ContractPayment contractPayment = contractPaymentRepository.findOne(ids[i]);
			if(contractPayment == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			setDiffAmount(contractPayment.getContractInfo(), contractPayment.getMaterialCost(), contractPayment.getArtificialCost(),
					contractPayment.getComprehensiveCost(), contractPayment.getManageCost(), contractPayment.getTax());//减去所有付款累计金额
			contractPaymentRepository.delete(contractPayment);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public ResultJson search(String contractInfo_name,String contractInfo_code, Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		
		Page<ContractPayment> contractPaymentList = contractPaymentRepository.findAll(new Specification<ContractPayment>(){
			@Override
			public Predicate toPredicate(Root<ContractPayment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 

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
		resultJson.setData(contractPaymentList);
		LOGGER.info("数据查询");
		return resultJson;
	}
	
	@Override
	public ResultJson importData(JSONArray contractPaymentArrary) {
		ResultJson resultJson = new ResultJson();
		//循环保存
		for (int i = 0; i < contractPaymentArrary.size(); i++) {
			JSONObject obj = contractPaymentArrary.getJSONObject(i);
			String ci_code = obj.getString("ci_code");
			ContractInfo contractInfo = contractInfoRepository.findByCode(ci_code);
			if(contractInfo == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			
			ContractPayment contractPayment = new ContractPayment();
			contractPayment.setTotalAmount(obj.getFloatValue("totalAmount"));
			contractPayment.setMaterialCost(obj.getFloatValue("materialCost"));
			contractPayment.setDiffArtificialCost(obj.getFloatValue("diffMaterialCost"));
			contractPayment.setArtificialCost(obj.getFloatValue("artificialCost"));
			contractPayment.setDiffArtificialCost(obj.getFloatValue("diffArtificialCost"));
			contractPayment.setComprehensiveCost(obj.getFloatValue("comprehensiveCost"));
			contractPayment.setDiffComprehensiveCost(obj.getFloatValue("diffComprehensiveCost"));
			contractPayment.setManageCost(obj.getFloatValue("manageCost"));
			contractPayment.setDiffManageCost(obj.getFloatValue("diffManageCost"));
			contractPayment.setTax(obj.getFloatValue("tax"));
			contractPayment.setDiffTax(obj.getFloatValue("diffTax"));
			contractPayment.setProfit(obj.getString("profit"));
			contractPayment.setProfitRate(obj.getString("profitRate"));
			contractPayment.setInputDate(obj.getDate("inputDate"));
			contractPayment.setContractInfo(contractInfo);
			setDiffAmount(contractInfo, obj.getFloatValue("diffMaterialCost"), obj.getFloatValue("diffArtificialCost"),
					obj.getFloatValue("diffComprehensiveCost"), obj.getFloatValue("diffManageCost"), obj.getFloatValue("diffTax"));//统计所有付款累计金额

			contractPaymentRepository.save(contractPayment);
		}
		
		resultJson.setSuccess(true);		
		resultJson.setData("数据已导入");
		LOGGER.info("创建实体");
		return resultJson;
	}
	
	/**
	 * 统计所有付款累计金额
	 * @author jiayiwu
	 * @date 2018年4月17日
	 * @param contractInfo
	 * @param diffAmount
	 */
	public void setDiffAmount(ContractInfo contractInfo,double diffMaterialCost,double diffArtificialCost,
			double diffComprehensiveCost,double diffManageCost,double diffTax) {
		double totalProAmount =0 ,totalMaterialCost =0 ,totalArtificialCost =0 ,totalComprehensiveCost =0 ,totalManageCost =0 ,totalTax =0 ;

		ContractAnalysis contractAnalysis = contractAnalysisRepository.findByContractInfo(contractInfo);
		if(contractAnalysis == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		totalMaterialCost = contractAnalysis.getPayMaterialCost()+diffMaterialCost;//材料累计投入
		totalArtificialCost = contractAnalysis.getPayArtificialCost()+diffArtificialCost;//人工累计投入
		totalComprehensiveCost = contractAnalysis.getPayComprehensiveCost()+diffComprehensiveCost;//综合累计投入
		totalManageCost = contractAnalysis.getPayManageCost()+diffManageCost;//管理累计投入
		totalTax = contractAnalysis.getPayTaxCost()+diffTax;//税金累计投入
		totalProAmount+=totalMaterialCost+totalArtificialCost+totalComprehensiveCost+totalManageCost+totalTax;//项目累计投入
		
		contractAnalysis.setPayMaterialCost(totalMaterialCost);
		contractAnalysis.setPayArtificialCost(totalArtificialCost);
		contractAnalysis.setPayComprehensiveCost(totalComprehensiveCost);
		contractAnalysis.setPayManageCost(totalManageCost);
		contractAnalysis.setPayTaxCost(totalTax);
		contractAnalysis.setPayTotalAmount(totalProAmount);
		
		contractAnalysisRepository.save(contractAnalysis);
	}
}
