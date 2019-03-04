package com.xhwl.erp.service.impl;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.domain.PriceHistoryRepository;
import com.xhwl.erp.domain.PriceRepository;
import com.xhwl.erp.entity.Account;
import com.xhwl.erp.entity.Price;
import com.xhwl.erp.entity.PriceHistory;
import com.xhwl.erp.service.PriceHistoryService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.Constants;

@Service
public class PriceHistoryServiceImpl implements PriceHistoryService{
	
	@Autowired
	private PriceHistoryRepository priceHistoryRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(priceRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		return resultJson;
	}
	
	@Override
	public ResultJson findAllByPrice(Long p_id,Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		Price price = priceRepository.findOne(p_id);
		if(price == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultJson.setData(priceHistoryRepository.findByPrice(price,pageable));
		resultJson.setSuccess(true);
		return resultJson;
	}
	
	@Override
	public PriceHistory save(JSONObject jsonObject) {
	    float productQuotation = jsonObject.getFloat("productQuotation");
	    Long price_id = jsonObject.getLong("price_id");
	    
	    //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Constants.SESSION_USER_INFO);
		
		PriceHistory priceHistory = new PriceHistory();
		priceHistory.setProductQuotation(productQuotation);//产品价格
		priceHistory.setTime(new Date());//当前时间
		priceHistory.setPerson(account.getName());
		
		Price price = priceRepository.findOne(price_id);
		priceHistory.setPrice(price);
		priceHistoryRepository.save(priceHistory);
		return priceHistory;
	}
	
}
