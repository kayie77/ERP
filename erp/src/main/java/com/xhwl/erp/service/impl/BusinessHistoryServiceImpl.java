package com.xhwl.erp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.domain.BusinessHistoryRepository;
import com.xhwl.erp.domain.BusinessRepository;
import com.xhwl.erp.entity.Account;
import com.xhwl.erp.entity.Business;
import com.xhwl.erp.entity.BusinessHistory;
import com.xhwl.erp.service.BusinessHistoryService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.Constants;

@Service
public class BusinessHistoryServiceImpl implements BusinessHistoryService{
	
	@Autowired
	private BusinessHistoryRepository businessHistoryRepository;
	
	@Autowired
	private BusinessRepository businessRepository;
	
	@Override
	public ResultJson findAllByBusiness(Long b_id,Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		Business business = businessRepository.findOne(b_id);
		if(business == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultJson.setData(businessHistoryRepository.findByBusiness(business, pageable));
		resultJson.setSuccess(true);
		return resultJson;
	}
	
	@Override
	public BusinessHistory save(JSONObject jsonObject) {
	    String content = jsonObject.getString("content");
	    int dNumber = jsonObject.getIntValue("dNumber");
	    Long business_id = jsonObject.getLong("business_id");
	    
	    //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Constants.SESSION_USER_INFO);
		
        BusinessHistory businessHistory = new BusinessHistory();
        businessHistory.setContent(content);//修改内容
        businessHistory.setTime(new Date());//当前时间
        businessHistory.setdNumber(dNumber);//间隔天数
        businessHistory.setPerson(account.getName());//当前用户
		
        Business business = businessRepository.findOne(business_id);
        businessHistory.setBusiness(business);
        businessHistoryRepository.save(businessHistory);
		return businessHistory;
	}
	
	@Override
	public ResultJson search(Long b_id,String content,Date time,Date time1,Pageable pageable){
		ResultJson resultJson = new ResultJson();
		
		Page<BusinessHistory> businessHistoryList = businessHistoryRepository.findAll(new Specification<BusinessHistory>(){
			@Override
			public Predicate toPredicate(Root<BusinessHistory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 
                if(null != content){
            			predicates.add(cb.like(root.get("content"), "%"+content+"%"));
                }
                if(null != time){
                    predicates.add(cb.between(root.get("time"), time, time1));
                }
                if(null != b_id){
                		Join<Business,BusinessHistory> join = root.join("business", JoinType.INNER);
                		predicates.add(cb.equal(join.get("id").as(Long.class),  b_id));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(businessHistoryList);
		return resultJson;
	}
	
}
