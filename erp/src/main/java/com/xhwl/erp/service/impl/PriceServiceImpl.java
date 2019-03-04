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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.PriceRepository;
import com.xhwl.erp.domain.SupplyRepository;
import com.xhwl.erp.entity.Price;
import com.xhwl.erp.entity.Supply;
import com.xhwl.erp.service.PriceService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class PriceServiceImpl implements PriceService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PriceServiceImpl.class);
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private SupplyRepository supplyRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(priceRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		    
		Price price = priceRepository.findOne(id);
		if(price == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("price", price);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(Price price) {
		ResultJson resultJson = new ResultJson();
		
		/**
		 * 设置产品编码
		 */
		String[] codeList = priceRepository.getCode();
		if(codeList.length==0) {
			price.setCode(""+20000000);
		}else {
			Integer maxIndex = 0;
			for (int i = 0; i < codeList.length; i++) {
				maxIndex = Integer.valueOf(codeList[0]);//定义最大值为该数组的第一个数
	            if(maxIndex <  Integer.valueOf(codeList[i])){    
	                maxIndex = Integer.valueOf(codeList[i]);
	            }
	        }
			price.setCode(String.valueOf(maxIndex+1));
		}
		
		/**
		 * 手动set关联实体
		 */
		if(price.getSupply()!=null) {
			Supply supply = supplyRepository.findOne(price.getSupply().getId());
			if(supply == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			price.setSupply(supply);
		}
		priceRepository.save(price);

		resultJson.setSuccess(true);	
		resultJson.setData(price);	
		LOGGER.info("创建实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			Price price = priceRepository.findOne(ids[i]);
			if(price == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			priceRepository.delete(price);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public ResultJson search(String name,String code,String type,String system,String specModel,String brand,Date startDate,Date endDate,String supply_name,Pageable pageable){
		ResultJson resultJson = new ResultJson();
		
		Page<Price> priceList = priceRepository.findAll(new Specification<Price>(){
			@Override
			public Predicate toPredicate(Root<Price> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 
				if(null != name){
                    predicates.add(cb.like(root.get("name"), "%"+name+"%"));
                }
				if(null != code){
                    predicates.add(cb.like(root.get("code"), "%"+code+"%"));
                }
                if(null != type){
                    predicates.add(cb.like(root.get("type"), "%"+type+"%"));
                }
                if(null != system){
                    predicates.add(cb.like(root.get("system"), "%"+system+"%"));
                }
                if(null != specModel){
                    predicates.add(cb.like(root.get("specModel"), "%"+specModel+"%"));
                }
                if(null != brand){
                    predicates.add(cb.like(root.get("brand"), "%"+brand+"%"));
                }
                if(null != startDate){
                    predicates.add(cb.between(root.get("startDate"), startDate, endDate));
                }
                if(null != supply_name){
                		predicates.add(cb.like(root.get("supply").get("name").as(String.class), "%"+supply_name+"%"));
                }
				
			    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(priceList);
		LOGGER.info("数据查询");
		return resultJson;
	}
}
