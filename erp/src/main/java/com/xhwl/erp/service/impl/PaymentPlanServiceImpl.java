package com.xhwl.erp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.ContractBasisRepository;
import com.xhwl.erp.domain.PaymentPlanRepository;
import com.xhwl.erp.entity.ContractBasis;
import com.xhwl.erp.entity.PaymentPlan;
import com.xhwl.erp.service.PaymentPlanService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class PaymentPlanServiceImpl implements PaymentPlanService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentPlanServiceImpl.class);
	
	@Autowired
	private PaymentPlanRepository paymentPlanRepository;
	
	@Autowired
	private ContractBasisRepository contractBasisRepository;
	
	@Override
	public ResultJson findAllByContractBasisAndPage(Long cb_id,Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		ContractBasis contractBasis = contractBasisRepository.findOne(cb_id);
		resultJson.setData(paymentPlanRepository.findByContractBasis(contractBasis, pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		PaymentPlan paymentPlan = paymentPlanRepository.findOne(id);
		if(paymentPlan == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("paymentPlan", paymentPlan);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(PaymentPlan paymentPlan) {
		ResultJson resultJson = new ResultJson();
		
		/**
		 * 设置合同开票外键
		 */
		if(paymentPlan.getContractBasis()!=null) {
			ContractBasis contractBasis = contractBasisRepository.findOne(paymentPlan.getContractBasis().getId());
			if(contractBasis == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			paymentPlan.setContractBasis(contractBasis);
		}
		
		/**
		 * 统计 计划回款累计金额
		 */
		List<PaymentPlan> array = paymentPlanRepository.getAll();
		Long nowDate = paymentPlan.getDate().getTime();
		double cumulativeAmount = paymentPlan.getCumulativeAmount();
		for (int i = 0; i < array.size(); i++) {
			PaymentPlan result = array.get(i);
			int res= nowDate.compareTo(result.getDate().getTime());
			if(res>0 || res==0) {
				cumulativeAmount+=result.getAmount();
			}
		}
		paymentPlan.setCumulativeAmount(cumulativeAmount+paymentPlan.getAmount());
		
		//保存
		paymentPlanRepository.save(paymentPlan);

		resultJson.setSuccess(true);		
		resultJson.setData(paymentPlan);	
		LOGGER.info("保存实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			PaymentPlan paymentPlan = paymentPlanRepository.findOne(ids[i]);
			if(paymentPlan == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			paymentPlanRepository.delete(paymentPlan);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}

}
