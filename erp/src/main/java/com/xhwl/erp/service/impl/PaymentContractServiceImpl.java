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

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.domain.ContractAnalysisRepository;
import com.xhwl.erp.domain.ContractInfoRepository;
import com.xhwl.erp.domain.InboundListRepository;
import com.xhwl.erp.domain.MaterialCategoryRepository;
import com.xhwl.erp.domain.SupplyRepository;
import com.xhwl.erp.domain.PaymentContractRepository;
import com.xhwl.erp.domain.RegionRepository;
import com.xhwl.erp.entity.ContractAnalysis;
import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.entity.InboundList;
import com.xhwl.erp.entity.MaterialCategory;
import com.xhwl.erp.entity.Supply;
import com.xhwl.erp.entity.PaymentContract;
import com.xhwl.erp.entity.Region;
import com.xhwl.erp.service.InboundListService;
import com.xhwl.erp.service.PaymentContractService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.CheckState;
import com.xhwl.erp.util.util.Constants;

@Service
public class PaymentContractServiceImpl implements PaymentContractService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentContractServiceImpl.class);
	
	@Autowired
	private ContractInfoRepository contractInfoRepository;
	
	@Autowired
	private PaymentContractRepository paymentContractRepository;
	
	@Autowired
	private SupplyRepository supplyRepository;
	
	@Autowired 
	private MaterialCategoryRepository materialCtgRepository;
	
	@Autowired
	private ContractAnalysisRepository contractAnalysisRepository;
	
	@Autowired
	private InboundListRepository inboundListRepository;
	
	@Autowired 
	private InboundListService inboundListService;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Override
	public ResultJson findAllByPage(String role_code,Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		
		Page<PaymentContract> paymentContractList = null;
		if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
			Region region = regionRepository.findByCode( role_code.substring(0, 4));
			paymentContractList = paymentContractRepository.findAllByRegion(region.getId(), pageable);
		}else {
			paymentContractList = paymentContractRepository.findAll(pageable);
		}
		
		resultJson.setData(paymentContractList);
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public JSONObject getTotalAmount(String role_code) {
		JSONObject returnData = new JSONObject();
		Object[] objArrary = null;
		double totalAdAmount =0,totalAcAmount=0,totalBillingAmount=0,totalPaymentAmount=0,totalPayableAmount=0;
		if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
			Region region = regionRepository.findByCode( role_code.substring(0, 4));
			objArrary = (Object[])paymentContractRepository.totalAmountArrary(region.getCode());
		}else {
			objArrary = (Object[])paymentContractRepository.totalAmountArrary();
}																				

		totalAdAmount = (double) objArrary[0];
		totalAcAmount = (double) objArrary[1];
		totalBillingAmount = (double) objArrary[2];
		totalPaymentAmount = (double) objArrary[3];
		totalPayableAmount = (double) objArrary[4];
		
		returnData.put("totalAdAmount", totalAdAmount);
		returnData.put("totalAcAmount", totalAcAmount);
		returnData.put("totalBillingAmount", totalBillingAmount);
		returnData.put("totalPaymentAmount", totalPaymentAmount);
		returnData.put("totalPayableAmount", totalPayableAmount);
			
		return returnData;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		    
		PaymentContract  paymentContract = paymentContractRepository.findOne(id);
		if(paymentContract == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("paymentContract", paymentContract);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(PaymentContract  paymentContract) {
		ResultJson resultJson = new ResultJson();
		double totalPurchaseAmount = 0;
		
		/**
		 * 设置付款合同号
		 */
		if(paymentContract.getSupply() !=null) {
			Supply supply = supplyRepository.findOne(paymentContract.getSupply().getId());
			if(supply == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			String orderCode = paymentContract.getOrderCode();//订单编号
			String code = orderCode + "-" + supply.getName();//付款合同号
			
			paymentContract.setCode(code);
			paymentContract.setSupply(supply);
	    }
		
		/**
		 * 设置合同外键
		 */
	    if(paymentContract.getContractInfo() !=null) {
	    		ContractInfo contractInfo= contractInfoRepository.findOne(paymentContract.getContractInfo().getId());
	    		if(contractInfo == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
	    		paymentContract.setContractInfo(contractInfo);
	    		
	    		/**
	    		 * 付款合同的业务类别直接关联合同所属的业务类别
	    		 */
	    		paymentContract.setCategory(contractInfo.getBusiness().getBusinessCategory().getName());
	    		
	    		/**
	    		 * 设置项目进度分析中的累计采购金额
	    		 */
			ContractAnalysis contractAnalysis = contractAnalysisRepository.findByContractInfo(contractInfo);
			if(contractAnalysis == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			
			totalPurchaseAmount = contractAnalysis.getTotalPurchaseAmount()+paymentContract.getAcAmount();//累计实际采购金额
			contractAnalysis.setTotalPurchaseAmount(totalPurchaseAmount);
			contractAnalysisRepository.save(contractAnalysis);
	    }
	    /**
		 * 设置材料类型外键
		 */
	    if(paymentContract.getMaterialCategory() !=null) {
	    		MaterialCategory materialCategory= materialCtgRepository.findOne(paymentContract.getMaterialCategory().getId());
	    		if(materialCategory == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
	    		paymentContract.setMaterialCategory(materialCategory);
	    }
	    
	    paymentContractRepository.save(paymentContract);//保存
	    
	    /**
	     * 根据发货状态修改入库单的情况
	     */
	    String deliveryStatus = paymentContract.getDeliveryStatus();
	    InboundList inboundList = null;
	    
	    switch (deliveryStatus) {
			case CheckState.AD_DELIVERY:
				inboundList = inboundListRepository.findByPaymentContract(paymentContract);
		    		if(inboundList == null) {
		    			inboundList = new InboundList();
		    			inboundList.setPaymentContract(paymentContract);
		    			inboundListService.save(inboundList);
		    		}
				break;
			case CheckState.AD_RETURN:
				inboundList = inboundListRepository.findByPaymentContract(paymentContract);
		    		if(inboundList !=null) inboundListRepository.delete(inboundList);
				break;
			case CheckState.UN_ELIVERY:
				inboundList = inboundListRepository.findByPaymentContract(paymentContract);
		    		if(inboundList !=null) inboundListRepository.delete(inboundList);
			default:
				break;
		}
		
	    resultJson.setData(paymentContract);
		resultJson.setSuccess(true);		
		LOGGER.info("创建实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			PaymentContract  paymentContract = paymentContractRepository.findOne(ids[i]);
			if(paymentContract == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			
			paymentContractRepository.delete(paymentContract);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public 	ResultJson search(String role_code,String orderCode,String code,String project,String department,String b_ctg_name,String m_ctg_name,
			String deliveryStatus,String contractInfo_code,String contractInfo_name,Date applicationTime,Date applicationTime1,double adAmount,double adAmount1,
			double acAmount,double acAmount1,Pageable pageable){
		ResultJson resultJson = new ResultJson();
		
		Page<PaymentContract> paymentContractList = paymentContractRepository.findAll(new Specification<PaymentContract>(){
			@Override
			public Predicate toPredicate(Root<PaymentContract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 
				if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
					Region region = regionRepository.findByCode( role_code.substring(0, 4));
					predicates.add(cb.equal(root.get("contractInfo").get("business").get("region").get("id").as(Long.class),  region.getId()));
				}
				if(null != orderCode){
                    predicates.add(cb.like(root.get("orderCode"), "%"+orderCode+"%"));
                }
				if(null != code){
                    predicates.add(cb.like(root.get("code"), "%"+code+"%"));
                }
				if(null != project){
                    predicates.add(cb.like(root.get("project"), "%"+project+"%"));
                }
				if(null != department){
                    predicates.add(cb.like(root.get("department"), "%"+department+"%"));
                }
                if(null != applicationTime){
                		predicates.add(cb.between(root.get("applicationTime"), applicationTime,applicationTime1));
                }
                if(0!= adAmount){
	            		predicates.add(cb.between(root.get("adAmount"), adAmount,adAmount1));
	            }
                if(0!= acAmount){
	            		predicates.add(cb.between(root.get("acAmount"), acAmount,acAmount1));
	            }
                if(null != deliveryStatus){
                    predicates.add(cb.like(root.get("deliveryStatus"), "%"+deliveryStatus+"%"));
                }
                if(null != b_ctg_name){
                		predicates.add(cb.like(root.get("contractInfo").get("business").get("businessCategory").get("name").as(String.class), "%"+b_ctg_name+"%"));
	            }
                if(null != m_ctg_name){
                		predicates.add(cb.like(root.get("materialCategory").get("name").as(String.class), "%"+m_ctg_name+"%"));
	            }
                if(null != contractInfo_code){
                		predicates.add(cb.like(root.get("contractInfo").get("code").as(String.class), "%"+contractInfo_code+"%"));
                }
                if(null != contractInfo_name){
	            		predicates.add(cb.like(root.get("contractInfo").get("name").as(String.class), "%"+contractInfo_name+"%"));
	            }
			    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(paymentContractList);
		LOGGER.info("数据查询");
		return resultJson;
	}
}
