package com.xhwl.erp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.SupplyRegionRepository;
import com.xhwl.erp.domain.SupplyRepository;
import com.xhwl.erp.entity.Supply;
import com.xhwl.erp.entity.SupplyRegion;
import com.xhwl.erp.service.SupplyService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class SupplyServiceImpl implements SupplyService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SupplyServiceImpl.class);
	
	@Autowired
	private SupplyRepository supplyRepository;
	
	@Autowired 
	private SupplyRegionRepository supplyRegionRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(supplyRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		    
		Supply supply= supplyRepository.findOne(id);
		if(supply == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultMap.put("supply", supply);
		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(Supply supply) {
		ResultJson resultJson = new ResultJson();
		
		/**
		 * 手动set关联实体
		 */
		if(supply.getSupplyRegion()!=null) {
			SupplyRegion supplyRegion = supplyRegionRepository.findOne(supply.getSupplyRegion().getId());
			if(supplyRegion == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			supply.setSupplyRegion(supplyRegion);
		}
		
		resultJson.setSuccess(true);		
		resultJson.setData(supplyRepository.save(supply));
		LOGGER.info("创建实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			Supply supply = supplyRepository.findOne(ids[i]);
			if(supply == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			supplyRepository.delete(supply);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public ResultJson search(String name,String materialCategory,String enterpriseNature,String cooperativeType,String category,
			String grade,String type,String supplyCycle,String reviewState,String settlementMethod,Long supply_region_id,Pageable pageable){
		ResultJson resultJson = new ResultJson();
		
		Page<Supply> supplyList = supplyRepository.findAll(new Specification<Supply>(){
			@Override
			public Predicate toPredicate(Root<Supply> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 
				if(null != name){
                    predicates.add(cb.like(root.get("name"), "%"+name+"%"));
                }
                if(null != materialCategory){
                    predicates.add(cb.like(root.get("materialCategory"), "%"+materialCategory+"%"));
                }
                if(null != enterpriseNature){
                    predicates.add(cb.like(root.get("enterpriseNature"), "%"+enterpriseNature+"%"));
                }
                if(null != enterpriseNature){
                    predicates.add(cb.like(root.get("enterpriseNature"), "%"+enterpriseNature+"%"));
                }
                if(null != cooperativeType){
                    predicates.add(cb.like(root.get("cooperativeType"), "%"+cooperativeType+"%"));
                }
                if(null != category){
                    predicates.add(cb.like(root.get("category"), "%"+category+"%"));
                }
                if(null != grade){
                    predicates.add(cb.like(root.get("grade"), "%"+grade+"%"));
                }
                if(null != type){
                    predicates.add(cb.like(root.get("type"), "%"+type+"%"));
                }
                if(null != supplyCycle){
                    predicates.add(cb.like(root.get("supplyCycle"), "%"+supplyCycle+"%"));
                }
                if(null != reviewState){
                    predicates.add(cb.like(root.get("reviewState"), "%"+reviewState+"%"));
                }
                if(null != settlementMethod){
                    predicates.add(cb.like(root.get("settlementMethod"), "%"+settlementMethod+"%"));
                }
                if(null != supply_region_id){
	            		Join<SupplyRegion,Supply> join = root.join("supplyRegion", JoinType.INNER);
	            		predicates.add(cb.equal(join.get("id").as(Long.class),  supply_region_id));
	            }
			    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(supplyList);
		LOGGER.info("数据查询");
		return resultJson;
	}
}
