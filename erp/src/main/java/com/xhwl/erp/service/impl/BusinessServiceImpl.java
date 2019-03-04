package com.xhwl.erp.service.impl;

import java.util.Date;
import java.text.SimpleDateFormat;
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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.BusinessCategoryRepository;
import com.xhwl.erp.domain.BusinessRepository;
import com.xhwl.erp.domain.CityRepository;
import com.xhwl.erp.domain.ClientRepository;
import com.xhwl.erp.domain.RegionRepository;
import com.xhwl.erp.domain.UserRepository;
import com.xhwl.erp.entity.Account;
import com.xhwl.erp.entity.Business;
import com.xhwl.erp.entity.BusinessCategory;
import com.xhwl.erp.entity.City;
import com.xhwl.erp.entity.Client;
import com.xhwl.erp.entity.Region;
import com.xhwl.erp.entity.User;
import com.xhwl.erp.service.BusinessService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.Constants;

@Service
public class BusinessServiceImpl implements BusinessService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessServiceImpl.class);
	
	@Autowired
	private BusinessRepository businessRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired 
	private CityRepository cityRepository;
	
	@Autowired 
	private ClientRepository clientRepository;
	
	@Autowired 
	private BusinessCategoryRepository businessCtgRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public ResultJson findAllByPage(String role_code,Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		Page<Business> businessList = null;
		if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
			Region region = regionRepository.findByCode( role_code.substring(0, 4));
			businessList = businessRepository.findByRegion(region, pageable);
		}else {
			businessList = businessRepository.findAll(pageable);
		}
		resultJson.setData(businessList);
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		    
		Business business = businessRepository.findOne(id);
		if(business == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		String oldCity = business.getOldCity();
		
		resultMap.put("oldCity", oldCity);
		resultMap.put("business", business);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(Business business) {
		ResultJson resultJson = new ResultJson();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
		Date sqlDate=new Date();
		//从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Constants.SESSION_USER_INFO);
		
        /**
		 * 当商机是新增的时候，设置新编码
		 */
		if(business.getId()==null) {
			/**
			 * 设置商机编码
			 */
			String[] codeList = businessRepository.getCodeStingList(sdf.format(sqlDate));
			if(codeList.length==0) {
				business.setCode(sdf.format(sqlDate)+""+100000);
			}else {
				Integer maxIndex = 0;
				for (int i = 0; i < codeList.length; i++) {
					maxIndex = Integer.valueOf(codeList[0]);//定义最大值为该数组的第一个数
		            if(maxIndex <  Integer.valueOf(codeList[i])){    
		                maxIndex = Integer.valueOf(codeList[i]);
		            }
		        }
				business.setCode(String.valueOf(maxIndex+1));
			}
		}
        
		/**
		 * 设置商机录入日期、创建人
		 */
		business.setDate(sqlDate);
		business.setCreatePerson(account.getName());
		
		/**
		 * 设置城市外键
		 */
		if(business.getCity() !=null) {
			City city  = cityRepository.findOne(business.getCity().getId());
			if(city == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			business.setCity(city);
		}
		/**
		 * 设置用户外键
		 */
		if(business.getClient()!=null) {
			Client client = clientRepository.findOne(business.getClient().getId());
			if(client == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			business.setClient(client);
		}
		/**
		 * 设置区域外键
		 */
		if(business.getRegion()!=null) {
			Region region = regionRepository.findOne(business.getRegion().getId());
			if(region == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			business.setRegion(region);
		}
		/**
		 * 设置业务分类外键
		 */
		if(business.getBusinessCategory()!=null) {
			BusinessCategory businessCategory = businessCtgRepository.findOne(business.getBusinessCategory().getId());
			if(businessCategory == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			business.setBusinessCategory(businessCategory);
		}
		/**
		 * 设置人员外键
		 */
		if(business.getBusinessPerson()!=null) business.setBusinessPerson(userRepository.findOne(business.getBusinessPerson().getId()));
		if(business.getDesignPerson()!=null) business.setDesignPerson(userRepository.findOne(business.getDesignPerson().getId()));
		if(business.getCostPerson()!=null) business.setCostPerson(userRepository.findOne(business.getCostPerson().getId()));
		if(business.getProjectPerson()!=null) business.setProjectPerson(userRepository.findOne(business.getProjectPerson().getId()));
		if(business.getProjectManager()!=null) business.setProjectManager(userRepository.findOne(business.getProjectManager().getId()));
		
		
		//保存
		businessRepository.save(business);

		resultJson.setSuccess(true);		
		resultJson.setData(business);
		LOGGER.info("创建实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			Business business = businessRepository.findOne(ids[i]);
			if(business == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			businessRepository.delete(business);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public ResultJson examine(Long id) {
		ResultJson resultJson = new ResultJson();
		Business business = businessRepository.findOne(id);
		if(business == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		business.setExamineState("有效商机");
		businessRepository.save(business);
		
		resultJson.setSuccess(true);
		resultJson.setData(business.getExamineState());
		LOGGER.info("商机审核");
		return resultJson;
		
	}
	
	@Override
	public ResultJson search(String role_code,String name,String code,String sourcePerson,Date startDate,Date startDate1,double amount,double amount1,
			String executeState,String examineState,Date date,Date date1,Long city_id,String client_name,String client_ctg,String bussinesCtg_name,Long bp_id,Long region_id,Pageable pageable){
		ResultJson resultJson = new ResultJson();
		
		Page<Business> businessList = businessRepository.findAll(new Specification<Business>(){
			@Override
			public Predicate toPredicate(Root<Business> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>(); 
				if(role_code.substring(0, 3).equals(Constants.ROLE_CODE)){
					Region region = regionRepository.findByCode(role_code.substring(0, 4));
	            		Join<Region,Business> join = root.join("region", JoinType.INNER);
	            		predicates.add(cb.equal(join.get("id").as(Long.class),  region.getId()));
	            }
				if(null != name){
	        			predicates.add(cb.like(root.get("name"), "%"+name+"%"));
	            }
				if(null != code){
	        			predicates.add(cb.like(root.get("code"), "%"+code+"%"));
	            }
                if(null != sourcePerson){
            			predicates.add(cb.like(root.get("sourcePerson"), "%"+sourcePerson+"%"));
                }
                if(null != startDate){
                    predicates.add(cb.between(root.get("startDate"), startDate, startDate1));
                }
                if(amount!=0){
                    predicates.add(cb.between(root.get("amount"), amount, amount1));
                }
                if(null != executeState){
	            	 	predicates.add(cb.equal(root.get("executeState"), executeState));
	            }
                if(null != examineState){
	            	 	predicates.add(cb.equal(root.get("examineState"), examineState));
	            }
                if(null != date){
                    predicates.add(cb.between(root.get("date"), date, date1));
                }
                if(null != examineState){
	            	 	predicates.add(cb.equal(root.get("examineState"), examineState));
	            }
                if(null != region_id){
                		Join<Region,Business> join = root.join("region", JoinType.INNER);
                		predicates.add(cb.equal(join.get("id").as(Long.class),  region_id));
                }
                if(null != city_id){
	            		Join<City,Business> join = root.join("city", JoinType.INNER);
	            		predicates.add(cb.equal(join.get("id").as(Long.class),  city_id));
	            }
                if(null != client_name){
                		predicates.add(cb.like(root.get("client").get("name").as(String.class), "%"+client_name+"%"));
	            }
                if(null != client_ctg){
                		predicates.add(cb.like(root.get("client").get("category").as(String.class), "%"+client_ctg+"%"));
	            }
                if(null != bussinesCtg_name){
	            		predicates.add(cb.like(root.get("businessCategory").get("name").as(String.class), "%"+bussinesCtg_name+"%"));
	            }
                if(null != bp_id){
	            		Join<User,Business> join = root.join("businessPerson", JoinType.INNER);
	            		predicates.add(cb.equal(join.get("id").as(Long.class),  bp_id));
	            }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(businessList);
		LOGGER.info("数据查询");
		return resultJson;
	}
}
