package com.xhwl.erp.service.impl;

import java.util.ArrayList;
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

import com.xhwl.erp.domain.BusinessRepository;
import com.xhwl.erp.domain.RegionRepository;
import com.xhwl.erp.domain.TenderOfferRepository;
import com.xhwl.erp.entity.Business;
import com.xhwl.erp.entity.Region;
import com.xhwl.erp.entity.TenderOffer;
import com.xhwl.erp.service.TenderOfferService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.Constants;

@Service
public class TenderOfferServiceImpl implements TenderOfferService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TenderOfferServiceImpl.class);
	
	@Autowired
	private TenderOfferRepository tenderOfferRepository;

	@Autowired 
	private BusinessRepository businessRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Override
	public ResultJson findAllByPage(String role_code,Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		Page<TenderOffer> tenderOfferList = null;
		if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
			Region region = regionRepository.findByCode(role_code.substring(0, 4));
			tenderOfferList = tenderOfferRepository.findAllByRegion(region.getId(), pageable);
		}else {
			tenderOfferList = tenderOfferRepository.findAll(pageable);
		}
		resultJson.setData(tenderOfferList);
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		    
		TenderOffer tenderOffer = tenderOfferRepository.findOne(id);
		if(tenderOffer == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("tenderOffer", tenderOffer);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(TenderOffer tenderOffer) {
		ResultJson resultJson = new ResultJson();

		/**
		 * 设置商机外键
		 */
		if(tenderOffer.getBusiness()!=null) {
			Business business  = businessRepository.findOne(tenderOffer.getBusiness().getId());
			if(business == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			tenderOffer.setBusiness(business);
		}
		
		/**
		 * 设置表格url
		 */
		String b_code = tenderOffer.getBusiness().getCode();
		if(b_code!=null) {
			tenderOffer.setUrl(Constants.EXCEL_URL+b_code);
		}
		
		//保存
		tenderOfferRepository.save(tenderOffer);

		resultJson.setSuccess(true);		
		resultJson.setData(tenderOffer);
		LOGGER.info("创建实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			TenderOffer tenderOffer = tenderOfferRepository.findOne(ids[i]);
			if(tenderOffer == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			tenderOfferRepository.delete(tenderOffer);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public ResultJson status(Long id) {
		ResultJson resultJson = new ResultJson();
		TenderOffer tenderOffer = tenderOfferRepository.findOne(id);
		if(tenderOffer == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		if(tenderOffer.getStatus()==0) {
			tenderOffer.setStatus(1);
			tenderOfferRepository.save(tenderOffer);
		}
		
		resultJson.setSuccess(true);
		resultJson.setData(tenderOffer.getStatus());
		LOGGER.info("设置状态");
		return resultJson;
		
	}
	
	@Override
	public ResultJson search(String role_code,String b_name,String b_code,Pageable pageable){
		ResultJson resultJson = new ResultJson();
		
		Page<TenderOffer> toList = tenderOfferRepository.findAll(new Specification<TenderOffer>(){
			@Override
			public Predicate toPredicate(Root<TenderOffer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 
				if(role_code.substring(0, 3).equals(Constants.ROLE_CODE)){
					Region region = regionRepository.findByCode( role_code.substring(0, 4));
					predicates.add(cb.equal(root.get("business").get("region").get("id").as(Long.class),  region.getId()));
	            }
				if(null != b_name){
	            		predicates.add(cb.like(root.get("business").get("name").as(String.class), "%"+b_name+"%"));
	            }
				if(null != b_code){
					predicates.add(cb.like(root.get("business").get("code").as(String.class), "%"+b_code+"%"));
	            }
			    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(toList);
		LOGGER.info("数据查询");
		return resultJson;
	}

}
