package com.xhwl.erp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.PaymentContractRepository;
import com.xhwl.erp.domain.PaymentRepository;
import com.xhwl.erp.entity.Payment;
import com.xhwl.erp.entity.PaymentContract;
import com.xhwl.erp.service.PaymentService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PaymentContractRepository paymentContractRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(paymentRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findAllByPaymentContractAndPage(Long pc_id,Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		PaymentContract paymentContract = paymentContractRepository.findOne(pc_id);
		resultJson.setData(paymentRepository.findByPaymentContract(paymentContract, pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Payment payment = paymentRepository.findOne(id);
		if(payment == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("payment", payment);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(Payment payment) {
		ResultJson resultJson = new ResultJson();
		double paymentAmount = 0;
		
		/**
		 * 手动set关联实体
		 */
		if(payment.getPaymentContract()!=null) {
			PaymentContract paymentContract = paymentContractRepository.findOne(payment.getPaymentContract().getId());
			if(paymentContract == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			payment.setPaymentContract(paymentContract);
			
			paymentAmount = paymentContract.getPaymentAmount()+payment.getAmount();//累计某个付款合同已付款金额
			paymentContract.setPaymentAmount(paymentAmount);
			paymentContractRepository.save(paymentContract);
		}
		
		//保存
		paymentRepository.save(payment);

		resultJson.setSuccess(true);		
		resultJson.setData(payment);	
		LOGGER.info("保存实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		ResultJson resultJson = new ResultJson();
		double paymentAmount = 0;
		
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			Payment payment = paymentRepository.findOne(ids[i]);
			if(payment == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			paymentRepository.delete(payment);
			
			PaymentContract paymentContract = payment.getPaymentContract();
			if(paymentContract == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			paymentAmount = paymentContract.getPaymentAmount()-payment.getAmount();//累计已开票金额
			paymentContract.setPaymentAmount(paymentAmount);
			paymentContractRepository.save(paymentContract);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}

}
