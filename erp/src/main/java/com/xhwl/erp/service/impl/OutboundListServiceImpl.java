package com.xhwl.erp.service.impl;

import java.util.Date;
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

import com.xhwl.erp.domain.InboundListRepository;
import com.xhwl.erp.domain.OutboundListRepository;
import com.xhwl.erp.domain.ProjectRepository;
import com.xhwl.erp.domain.RegionRepository;
import com.xhwl.erp.entity.Account;
import com.xhwl.erp.entity.InboundList;
import com.xhwl.erp.entity.OutboundList;
import com.xhwl.erp.entity.Project;
import com.xhwl.erp.entity.Region;
import com.xhwl.erp.service.OutboundListService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.CheckState;
import com.xhwl.erp.util.util.Constants;

@Service
public class OutboundListServiceImpl implements OutboundListService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OutboundListServiceImpl.class);
	
	@Autowired
	private OutboundListRepository  outboundListRepository;
	
	@Autowired
	private InboundListRepository  inboundListRepository;
	
	@Autowired
	private  ProjectRepository  projectRepository;
	
	@Autowired
	private  RegionRepository  regionRepository;

	
	@Override
	public ResultJson findAllByPage(String role_code,Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		
		Page<OutboundList> outboundListList = null;
		if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
			Region region = regionRepository.findByCode(role_code.substring(0, 4));
			outboundListList = outboundListRepository.findAllByRegion(region.getId(), pageable);
		}else {
			outboundListList = outboundListRepository.findAll(pageable);
		}
		
		resultJson.setData(outboundListList);
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		    
		OutboundList outboundList = outboundListRepository.findOne(id);
		if(outboundList == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		Iterable<InboundList> inboundListArrary= inboundListRepository.findAll();
		Iterable<Project> projectList = projectRepository.findAll();
		Iterable<Region> regionList = regionRepository.findAll();
		
		resultMap.put("inboundListArrary", inboundListArrary);
		resultMap.put("projectList", projectList);
		resultMap.put("regionList", regionList);
		resultMap.put("outboundList", outboundList);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(OutboundList outboundList) {
		ResultJson resultJson = new ResultJson();
		//从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Constants.SESSION_USER_INFO);
		
		/**
		 * 手动set关联实体
		 */
		if(outboundList.getInboundList()!=null) {
			InboundList inboundList = inboundListRepository.findOne(outboundList.getInboundList().getId());
			if(inboundList == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			outboundList.setInboundList(inboundList);
			outboundList.setTableCode("CX-"+inboundList.getTableCode().substring(2));//设置出库成本核算表号
		}
		if(outboundList.getProject()!=null) {
			Project project = projectRepository.findOne(outboundList.getProject().getId());
			if(project == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			outboundList.setProject(project);
		}
		if(outboundList.getRegion()!=null) {
			Region region = regionRepository.findOne(outboundList.getRegion().getId());
			if(region == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			outboundList.setRegion(region);
		}

		outboundList.setState(CheckState.UN_AUDIT);//设置状态
		outboundList.setPerson(account.getName());//设置制表人
		
		//保存
		outboundListRepository.save(outboundList);

		resultJson.setSuccess(true);		 
		resultJson.setData(outboundList);	
		LOGGER.info("创建实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			OutboundList outboundList = outboundListRepository.findOne(ids[i]);
			if(outboundList == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			outboundListRepository.delete(outboundList);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
	}
	
	@Override
	public ResultJson search(String role_code,String inboundList_code, String code,Long region_id,String contractInfo_name,Date date,Date date1,Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		
		Page<OutboundList> outboundList = outboundListRepository.findAll(new Specification<OutboundList>(){
			@Override
			public Predicate toPredicate(Root<OutboundList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 
				if( role_code.substring(0, 3).equals(Constants.ROLE_CODE)) {
					Region region = regionRepository.findByCode( role_code.substring(0, 4));
					predicates.add(cb.equal(root.get("inboundList").get("paymentContract").get("contractInfo").get("business").get("region").get("id").as(Long.class),  region.getId()));
				}
				if(null != inboundList_code){
					predicates.add(cb.like(root.get("inboundList").get("code").as(String.class), "%"+inboundList_code+"%"));
	            }
				if(null != code){
                    predicates.add(cb.like(root.get("code"), "%"+code+"%"));
                }
				if(null != region_id){
	            	    Join<Region,OutboundList> join = root.join("region", JoinType.INNER);
	            		predicates.add(cb.equal(join.get("id").as(Long.class),  region_id));
	            }
				if(null != contractInfo_name){
					predicates.add(cb.like(root.get("inboundList").get("paymentContract").get("contractInfo").get("name").as(String.class), "%"+contractInfo_name+"%"));
	            }
                if(null != date){
                    predicates.add(cb.between(root.get("date"), date, date1));
                }
			    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(outboundList);
		LOGGER.info("数据查询");
		return resultJson;
	}

}
