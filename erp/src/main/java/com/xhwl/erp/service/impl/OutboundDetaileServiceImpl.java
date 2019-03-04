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
import com.xhwl.erp.domain.OutboundListRepository;
import com.xhwl.erp.domain.PurchaseListRepository;
import com.xhwl.erp.entity.InboundDetaile;
import com.xhwl.erp.entity.OutboundDetaile;
import com.xhwl.erp.entity.OutboundList;
import com.xhwl.erp.entity.PurchaseList;
import com.xhwl.erp.service.OutboundDetaileService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class OutboundDetaileServiceImpl implements OutboundDetaileService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OutboundDetaileServiceImpl.class);
	
	@Autowired
	private OutboundListRepository  outboundListRepository;
	
	@Autowired
	private OutboundDetaileRepository outboundDetaileRepository;
	
	@Autowired
	private InboundDetaileRepository inboundDetaileRepository;
	
	@Autowired
	private PurchaseListRepository purchaseListRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(outboundDetaileRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findByOutboundList(Long ol_id,Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		OutboundList outboundList = outboundListRepository.findOne(ol_id);
		if(outboundList == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultJson.setData(outboundDetaileRepository.findByOutboundList(outboundList, pageable));
		resultJson.setSuccess(true);
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		ResultJson resultJson = new ResultJson();
		OutboundDetaile outboundDetaile = outboundDetaileRepository.findOne(id);
		if(outboundDetaile == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultJson.setData(outboundDetaile);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(List<OutboundDetaile > outboundDetaileArrary) {
		ResultJson resultJson = new ResultJson();
		List<OutboundDetaile> result = new ArrayList<OutboundDetaile>();
		
		for(OutboundDetaile outboundDetaile :outboundDetaileArrary) {
			/**
			 * 手动set关联实体
			 */
			if(outboundDetaile.getOutboundList()!=null) {
				OutboundList outboundList = outboundListRepository.findOne(outboundDetaile.getOutboundList().getId());
				if(outboundList == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
				outboundDetaile.setOutboundList(outboundList);
			}
			if(outboundDetaile.getPurchaseList()!=null) {
				PurchaseList purchaseList = purchaseListRepository.findOne(outboundDetaile.getPurchaseList().getId());
				if(purchaseList == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
				/**
				 * 设置产品剩余数量
				 */
				InboundDetaile inboundDetaile =  inboundDetaileRepository.findByPurchaseList(purchaseList);
				if(inboundDetaile == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
				int surplusNumber = inboundDetaile.getSurplusNumber() + outboundDetaile.getDiffNumber();
				inboundDetaile.setSurplusNumber(surplusNumber);
				inboundDetaileRepository.save(inboundDetaile);
				outboundDetaile.setPurchaseList(purchaseList);
			}
			
			//保存
			result.add(outboundDetaileRepository.save(outboundDetaile));
			
		}
		resultJson.setSuccess(true);		
		resultJson.setData(result);
		LOGGER.info("创建实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			OutboundDetaile outboundDetaile = outboundDetaileRepository.findOne(ids[i]);
			if(outboundDetaile == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			/**
			 * 设置产品剩余数量
			 */
			PurchaseList purchaseList = purchaseListRepository.findOne(outboundDetaile.getPurchaseList().getId());
			InboundDetaile inboundDetaile =  inboundDetaileRepository.findByPurchaseList(purchaseList);
			int surplusNumber = inboundDetaile.getSurplusNumber() + outboundDetaile.getNumber();
			inboundDetaile.setSurplusNumber(surplusNumber);
			inboundDetaileRepository.save(inboundDetaile);
			
			outboundDetaileRepository.delete(outboundDetaile);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
	}
}
