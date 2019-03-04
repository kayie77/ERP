package com.xhwl.erp.service.impl;

import java.util.Date;
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

import com.xhwl.erp.domain.ContractInfoRepository;
import com.xhwl.erp.domain.ContractScheduleRepository;
import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.entity.ContractSchedule;
import com.xhwl.erp.service.ContractScheduleService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class ContractScheduleServiceImpl implements ContractScheduleService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractScheduleServiceImpl.class);
	
	@Autowired
	private ContractScheduleRepository contractScheduleRepository;
	
	@Autowired
	private ContractInfoRepository contractInfoRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(contractScheduleRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findAllByContractInfo(Long ci_id) {
		
		ResultJson resultJson = new ResultJson();
		ContractInfo contractInfo = contractInfoRepository.findOne(ci_id);
		ContractSchedule contractSchedule = contractScheduleRepository.findByContractInfo(contractInfo);
		
		resultJson.setData(contractSchedule);
		resultJson.setSuccess(true);
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		ContractSchedule contractSchedule = contractScheduleRepository.findOne(id);
		if(contractSchedule == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultJson.setData(contractSchedule);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(ContractSchedule contractSchedule) {
		
		ResultJson resultJson = new ResultJson();
		/**
		 * 设置系统时间
		 */
		contractSchedule.setTime(new Date());
		
		/**
		 * 手动set关联实体
		 */
		if(contractSchedule.getContractInfo()!=null) {
			ContractInfo contractInfo = contractInfoRepository.findOne(contractSchedule.getContractInfo().getId());
			if(contractInfo == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			contractSchedule.setContractInfo(contractInfo);
		}

		//保存
		contractScheduleRepository.save(contractSchedule);

		resultJson.setSuccess(true);	
		resultJson.setData(contractSchedule);
		LOGGER.info("保存实体");
		return resultJson;
	}
	
	@Override
	public ResultJson search(String stage,String projectStatus,String receivedStatus,String materialStatus, String artificialStatus,
			String comprehensiveStatus,String paymentBalanceStatus,String cashBalanceStatus,String contractInfo_name,String contractInfo_code, Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		
		Page<ContractSchedule> contractScheduleList = contractScheduleRepository.findAll(new Specification<ContractSchedule>(){
			@Override
			public Predicate toPredicate(Root<ContractSchedule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 

				if(null != stage){
					predicates.add(cb.equal(root.get("stage"), stage));
	            }
				if(null != projectStatus){
					predicates.add(cb.equal(root.get("projectStatus"), projectStatus));
	            }
				if(null != receivedStatus){
					predicates.add(cb.equal(root.get("receivedStatus"), receivedStatus));
	            }
				if(null != materialStatus){
					predicates.add(cb.equal(root.get("materialStatus"), materialStatus));
	            }
				if(null != artificialStatus){
					predicates.add(cb.equal(root.get("artificialStatus"), artificialStatus));
	            }
				if(null != comprehensiveStatus){
					predicates.add(cb.equal(root.get("comprehensiveStatus"), comprehensiveStatus));
	            }
				if(null != paymentBalanceStatus){
					predicates.add(cb.equal(root.get("paymentBalanceStatus"), paymentBalanceStatus));
	            }
				if(null != cashBalanceStatus){
					predicates.add(cb.equal(root.get("cashBalanceStatus"), cashBalanceStatus));
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
		resultJson.setData(contractScheduleList);
		LOGGER.info("数据查询");
		return resultJson;
	}
	
//	@Override
//	public ContractSchedule analysis(ContractInfo contractInfo) {
//		//根据合同获取项目进度、项目分析实体
//		ContractAnalysis contractAnalysis = contractAnalysisRepository.findByContractInfo(contractInfo);
//		if(contractAnalysis == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
//		ContractSchedule contractSchedule = contractScheduleRepository.findByContractInfo(contractInfo);
//		if(contractSchedule == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
//		//获取项目分析实体中保存的字段
//		float totalBillingAmount = contractAnalysis.getTotalBillingAmount();//累计开票金额
//		float totalReceivedAmount = contractAnalysis.getTotalReceivedAmount();//累计回款金额
//		float totalPurchaseAmount = contractAnalysis.getTotalPurchaseAmount();//累计采购金额
//		float contractTotalAmount = contractAnalysis.getContractTotalAmount();//合同总额
//		float basisMaterialCost = contractAnalysis.getBasisMaterialCost();//材料支出成本（交底）
//		float basisArtificialCost = contractAnalysis.getBasisArtificialCost();//人工支出成本（交底）
//		float basisComprehensiveCost = contractAnalysis.getBasisComprehensiveCost();//综合支出成本（交底）
//		float payTotalAmount = contractAnalysis.getPayTotalAmount();//累计投入金额（付款）
//		float payArtificialCost = contractAnalysis.getPayArtificialCost();//人工累计投入（付款）
//		float payComprehensiveCost = contractAnalysis.getPayComprehensiveCost();//综合累计投入（付款
//		float finishPercentage = contractSchedule.getFinishPercentage();//完工百分比
//		//设置所有计算金额
//		float billingPercentage=0,receivedPercentage=0,materialPercentage=0,artificialPercentage=0,comprehensivePercentage=0;
//		if(contractTotalAmount!=0)  billingPercentage = totalBillingAmount/contractTotalAmount;//开票百分比 = “累计开票金额”/“合同总额”
//		if(totalBillingAmount!=0) receivedPercentage = totalReceivedAmount/totalBillingAmount;//回款百分比 = “累计回款金额”/“累计开票金额”
//		if(basisMaterialCost!=0)  materialPercentage = totalPurchaseAmount/basisMaterialCost;//材料采购百分比 = 付款合同管理模块累计采购金额/交底支出“材料成本”
//		if(basisArtificialCost!=0) artificialPercentage = payArtificialCost/basisArtificialCost;//人工支出百分比 = “人工累计投入”/ 交底支出“人工成本”
//		if(basisComprehensiveCost!=0) comprehensivePercentage = payComprehensiveCost/basisComprehensiveCost;//综合支出百分比 = “综合累计投入”/ 交底支出“综合成本”
//		float paymentBalance = totalBillingAmount-payTotalAmount;//收支差额 = “累计开票金额”-“累计投入金额”
//		float cashBalance = totalReceivedAmount-payTotalAmount;//付现差额 = “累计回款金额”-“累计投入金额”
//		
//		contractSchedule.setBillingPercentage(billingPercentage);
//		contractSchedule.setReceivedPercentage(receivedPercentage);
//		contractSchedule.setMaterialPercentage(materialPercentage);
//		contractSchedule.setArtificialPercentage(artificialPercentage);
//		contractSchedule.setComprehensivePercentage(comprehensivePercentage);
//		contractSchedule.setPaymentBalance(paymentBalance);
//		contractSchedule.setCashBalance(cashBalance);
//		
//		//项目状态：完工百分比-开票百分比 
//		if((finishPercentage-billingPercentage)<=0.15) {
//			contractSchedule.setProjectStatus("正常");
//		}else if((finishPercentage-billingPercentage)>0.15 && (finishPercentage-billingPercentage)<0.3){
//			contractSchedule.setProjectStatus("滞后");
//		}else {
//			contractSchedule.setProjectStatus("严重滞后");
//		}
//		//回款状态：回款比例
//		if(receivedPercentage>=0.85) {
//			contractSchedule.setReceivedStatus("正常");
//		}else if(receivedPercentage<0.85 && receivedPercentage>0.6){
//			contractSchedule.setReceivedStatus("滞后");
//		}else {
//			contractSchedule.setReceivedStatus("严重滞后");
//		}
//		//材料采购状态：采购比例-完工百分比
//		if((materialPercentage-finishPercentage)>=0.15) {
//			contractSchedule.setMaterialStatus("提前");
//		}else if((materialPercentage-finishPercentage)<0.15 && (materialPercentage-finishPercentage)>0){
//			contractSchedule.setMaterialStatus("正常");
//		}else if((materialPercentage-finishPercentage)<=0 && (materialPercentage-finishPercentage)>-0.15) {
//			contractSchedule.setMaterialStatus("滞后");
//		}else {
//			contractSchedule.setMaterialStatus("严重滞后");
//		}
//		//人工支出状态：“支出比例” 对比 “完工百分比”
//		if(artificialPercentage>finishPercentage) {
//			contractSchedule.setArtificialStatus("超支");
//		}else {
//			contractSchedule.setArtificialStatus("正常");
//		}
//		//综合支出状态：“支出比例” 对比 “完工百分比”
//		if(comprehensivePercentage>finishPercentage) {
//			contractSchedule.setComprehensiveStatus("超支");
//		}else {
//			contractSchedule.setComprehensiveStatus("正常");
//		}
//		//收入成本差异状态:<0 >=0
//		if(paymentBalance<0) {
//			contractSchedule.setPaymentBalanceStatus("超支");
//		}else {
//			contractSchedule.setPaymentBalanceStatus("正常");
//		}
//		//付现差异状态:<0 >=0
//		if(cashBalance<0) {
//			contractSchedule.setCashBalanceStatus("超支");
//		}else {
//			contractSchedule.setCashBalanceStatus("正常");
//		}
//		
//		//保存实体
//		//contractScheduleRepository.save(contractSchedule);
//		
//		return contractSchedule;
//	}
}
