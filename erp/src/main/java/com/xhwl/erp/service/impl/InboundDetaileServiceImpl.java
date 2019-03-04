package com.xhwl.erp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.InboundDetaileRepository;
import com.xhwl.erp.domain.OutboundDetaileRepository;
import com.xhwl.erp.domain.PaymentContractRepository;
import com.xhwl.erp.domain.PurchaseListRepository;
import com.xhwl.erp.entity.InboundDetaile;
import com.xhwl.erp.entity.OutboundDetaile;
import com.xhwl.erp.entity.PaymentContract;
import com.xhwl.erp.entity.PurchaseList;
import com.xhwl.erp.service.InboundDetaileService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class InboundDetaileServiceImpl implements InboundDetaileService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InboundDetaileServiceImpl.class);
	
	@Autowired
	private InboundDetaileRepository  inboundDetaileRepository;
	
	@Autowired
	private PurchaseListRepository  purchaseListRepository;
	
	@Autowired
	private  PaymentContractRepository  paymentContractRepository;
	
	@Autowired
	private  OutboundDetaileRepository outboundDetaileRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(inboundDetaileRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findAllByPaymentContract(Long pc_id) {
		ResultJson resultJson = new ResultJson();
		List<InboundDetaile> result = new ArrayList<InboundDetaile>();
		
		PaymentContract paymentContract = paymentContractRepository.findOne(pc_id);//获得采购合同
		if(paymentContract == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		List<PurchaseList> purchaseListArrary = purchaseListRepository.findByPaymentContract(paymentContract);//根据采购合同获得采购清单
		for (PurchaseList purchaseList : purchaseListArrary) {
			InboundDetaile inboundDetaile = inboundDetaileRepository.findByPurchaseList(purchaseList);//根据采购清单获得入库明细
			result.add(inboundDetaile);
		}
		resultJson.setSuccess(true);
		resultJson.setData(result);
		return resultJson;
	}
	
	@Override
	public ResultJson save(InboundDetaile inboundDetaile) {
		ResultJson resultJson = new ResultJson();
		
		/**
		 * 手动set关联实体
		 */
		if(inboundDetaile.getPurchaseList()!=null) {
			PurchaseList purchaseList = purchaseListRepository.findOne(inboundDetaile.getPurchaseList().getId());
			if(purchaseList == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			inboundDetaile.setPurchaseList(purchaseList);
			
			OutboundDetaile outboundDetaile = outboundDetaileRepository.findByPurchaseList(purchaseList);
		    if(outboundDetaile!=null) {//设置剩余数量为当前入库数量减去已出库数量
		    		int out_number = outboundDetaile.getNumber();
		    		inboundDetaile.setSurplusNumber(inboundDetaile.getNumber()-out_number);
		    }else {
		    		inboundDetaile.setSurplusNumber(inboundDetaile.getNumber());
		    }
		}
		
		//保存
		inboundDetaileRepository.save(inboundDetaile);

		resultJson.setSuccess(true);		
		resultJson.setData(inboundDetaile);
		LOGGER.info("保存实体");
		return resultJson;
	}
}
