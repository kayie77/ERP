package com.xhwl.erp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.BillingRepository;
import com.xhwl.erp.domain.PaymentContractRepository;
import com.xhwl.erp.entity.Billing;
import com.xhwl.erp.entity.PaymentContract;
import com.xhwl.erp.service.BillingService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class BillingServiceImpl implements BillingService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BillingServiceImpl.class);
	
	@Autowired
	private BillingRepository billingRepository;
	
	@Autowired
	private PaymentContractRepository paymentContractRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(billingRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findAllByPaymentContractAndPage(Long pc_id,Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		PaymentContract paymentContract = paymentContractRepository.findOne(pc_id);
		resultJson.setData(billingRepository.findByPaymentContract(paymentContract, pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Billing billing = billingRepository.findOne(id);
		if(billing == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("billing", billing);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(Billing billing) {
		ResultJson resultJson = new ResultJson();
		double billingAmount = 0;
		
		/**
		 * 手动set关联实体
		 */
		if(billing.getPaymentContract()!=null) {
			PaymentContract paymentContract = paymentContractRepository.findOne(billing.getPaymentContract().getId());
			if(paymentContract == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			billing.setPaymentContract(paymentContract);
			
			billingAmount = paymentContract.getBillingAmount()+billing.getAmount();//累计某个付款合同已开票金额
			paymentContract.setBillingAmount(billingAmount);
			paymentContractRepository.save(paymentContract);
		}
		
		//保存
		billingRepository.save(billing);

		resultJson.setSuccess(true);		
		resultJson.setData(billing);
		LOGGER.info("保存实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		ResultJson resultJson = new ResultJson();
		double billingAmount = 0;
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			Billing billing = billingRepository.findOne(ids[i]);
			if(billing == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			billingRepository.delete(billing);
			
			PaymentContract paymentContract = billing.getPaymentContract();
			if(paymentContract == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			billingAmount = paymentContract.getBillingAmount()-billing.getAmount();//累计已开票金额
			paymentContract.setBillingAmount(billingAmount);
			paymentContractRepository.save(paymentContract);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}

}
