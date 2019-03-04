package com.xhwl.erp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.domain.InboundDetaileRepository;
import com.xhwl.erp.domain.PaymentContractRepository;
import com.xhwl.erp.domain.PurchaseListRepository;
import com.xhwl.erp.entity.InboundDetaile;
import com.xhwl.erp.entity.PaymentContract;
import com.xhwl.erp.entity.PurchaseList;
import com.xhwl.erp.service.PurchaseListService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class PurchaseListServiceImpl implements PurchaseListService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseListServiceImpl.class);
	
	@Autowired
	private  PurchaseListRepository  purchaseListRepository;
	
	@Autowired
	private  InboundDetaileRepository inboundDetaileRepository;
	
	@Autowired
	private  PaymentContractRepository  paymentContractRepository;
	
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(purchaseListRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findAllByPaymentContract(Long pc_id) {
		
		ResultJson resultJson = new ResultJson();
		PaymentContract paymentContract = paymentContractRepository.findOne(pc_id);
		if(paymentContract == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultJson.setData(purchaseListRepository.findByPaymentContract(paymentContract));
		resultJson.setSuccess(true);
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		    
		PurchaseList purchaseList = purchaseListRepository.findOne(id);
		if(purchaseList == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
	
		resultMap.put("purchaseList", purchaseList);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson importData(List<PurchaseList> purchaseListArrary,Long pc_id) {
		ResultJson resultJson = new ResultJson();
		List<PurchaseList> newResult = new ArrayList<PurchaseList>();
		//获取付款合同实体
		PaymentContract paymentContract = paymentContractRepository.findOne(pc_id);
		if(paymentContract == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
		//通过付款合同实体获得已存在的物料清单数组
		List<PurchaseList> oldResult = purchaseListRepository.findByPaymentContract(paymentContract);
		if(oldResult != null) {//如果非空，循环删除已存在的物料清单数组
			for(PurchaseList purchaseList :oldResult) {
				purchaseListRepository.delete(purchaseList);
			}
		}
		//循环保存
		for(PurchaseList purchaseList :purchaseListArrary) {
			purchaseList.setPaymentContract(paymentContract);//设置付款合同外键
			if(purchaseList.getId()==null) {
				/**
				 *  当采购清单是新增数据时，需要新增入库明细实体，如果是编辑，则不需要新增
				 */
				InboundDetaile inboundDetaile = new InboundDetaile();
				inboundDetaileRepository.save(inboundDetaile);
				inboundDetaile.setPurchaseList(purchaseList);
			}
			//保存
			newResult.add(purchaseListRepository.save(purchaseList));
		}

		resultJson.setSuccess(true);		
		resultJson.setData(newResult);
		LOGGER.info("创建实体");
		return resultJson;
	}
	
	@Override
	public PurchaseList save(JSONObject jsonObject) {
		int acNumber = jsonObject.getIntValue("acNumber");
		float acAmount = jsonObject.getFloatValue("acAmount");
	    Long id = jsonObject.getLong("id");
		
	    PurchaseList purchaseList = purchaseListRepository.findOne(id);
	    if(purchaseList == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
	    purchaseList.setAcNumber(acNumber);
	    purchaseList.setAcAmount(acAmount);
	    
		//保存
	    purchaseListRepository.save(purchaseList);
		LOGGER.info("保存实体");
		return purchaseList;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			PurchaseList purchaseList = purchaseListRepository.findOne(ids[i]);
			if(purchaseList == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			purchaseListRepository.delete(purchaseList);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}

}
