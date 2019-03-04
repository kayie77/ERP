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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.InboundListRepository;
import com.xhwl.erp.domain.PaymentContractRepository;
import com.xhwl.erp.domain.RegionRepository;
import com.xhwl.erp.entity.Account;
import com.xhwl.erp.entity.InboundList;
import com.xhwl.erp.entity.PaymentContract;
import com.xhwl.erp.entity.Region;
import com.xhwl.erp.service.InboundListService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.CheckState;
import com.xhwl.erp.util.util.Constants;

@Service
public class InboundListServiceImpl implements InboundListService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InboundListServiceImpl.class);
	
	@Autowired
	private PaymentContractRepository paymentContractRepository;
	
	@Autowired
	private InboundListRepository inboundListRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Override
	public ResultJson findAllByPage(String role_code,Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		
		Page<InboundList> inboundListList = null;
		if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
			Region region = regionRepository.findByCode( role_code.substring(0, 4));
			inboundListList = inboundListRepository.findAllByRegion(region.getId(), pageable);
		}else {
			inboundListList = inboundListRepository.findAll(pageable);
		}
		
		resultJson.setData(inboundListList);
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		    
		InboundList  inboundList = inboundListRepository.findOne(id);
		if(inboundList == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		resultMap.put("inboundList", inboundList);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(InboundList inboundList) {
		ResultJson resultJson = new ResultJson();
		//从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Constants.SESSION_USER_INFO);
		
	    if(inboundList.getPaymentContract() !=null) {//设置所属付款合同
	    		PaymentContract paymentContract= paymentContractRepository.findOne(inboundList.getPaymentContract().getId());
	    		if(paymentContract == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
	    		inboundList.setPaymentContract(paymentContract);
	    		
	    		String code = paymentContract.getCode();//入库单号与付款合同号一致
	    		String tableCode = "RX-"+paymentContract.getOrderCode().substring(4)+ "-" + paymentContract.getSupply().getName();//入库成本核算表号
	    		inboundList.setCode(code);
	    		inboundList.setTableCode(tableCode);
	    		inboundList.setDate(new Date());//设置系统入库时间
	    }
	    
	    inboundList.setState(CheckState.UN_AUDIT);//设置状态
	    inboundList.setPerson(account.getName());//设置制表人
		
		//保存
	    resultJson.setData(inboundListRepository.save(inboundList));
		resultJson.setSuccess(true);		
		LOGGER.info("创建实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			InboundList  inboundList = inboundListRepository.findOne(ids[i]);
			if(inboundList == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			inboundListRepository.delete(inboundList);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public 	ResultJson search(String role_code,String orderCode,String code,Date date,Date date1,String department,String supply_name,Pageable pageable){
		ResultJson resultJson = new ResultJson();
		
		Page<InboundList> inboundList = inboundListRepository.findAll(new Specification<InboundList>(){
			@Override
			public Predicate toPredicate(Root<InboundList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 
				if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
					Region region = regionRepository.findByCode( role_code.substring(0, 4));
					predicates.add(cb.equal(root.get("paymentContract").get("contractInfo").get("business").get("region").get("id").as(Long.class),  region.getId()));
				}
				if(null != orderCode){
					predicates.add(cb.equal(root.get("paymentContract").get("orderCode").as(String.class), orderCode));
                }
				if(null != code){
                    predicates.add(cb.like(root.get("code"), "%"+code+"%"));
                }
				if(null != department){
					predicates.add(cb.equal(root.get("paymentContract").get("department").as(String.class), department));
                }
                if(null != date){
                		predicates.add(cb.between(root.get("date"), date,date1));
                }
                if(null != supply_name){
                		predicates.add(cb.like(root.get("paymentContract").get("supply").get("name").as(String.class), "%"+supply_name+"%"));
	            }
			    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(inboundList);
		LOGGER.info("数据查询");
		return resultJson;
	}
}
