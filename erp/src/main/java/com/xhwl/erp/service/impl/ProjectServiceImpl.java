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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.CityRepository;
import com.xhwl.erp.domain.ClientRepository;
import com.xhwl.erp.domain.ProjectDesignRepository;
import com.xhwl.erp.domain.ProjectRepository;
import com.xhwl.erp.domain.RegionRepository;
import com.xhwl.erp.entity.City;
import com.xhwl.erp.entity.Client;
import com.xhwl.erp.entity.Project;
import com.xhwl.erp.entity.ProjectDesign;
import com.xhwl.erp.entity.Region;
import com.xhwl.erp.service.ProjectService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class ProjectServiceImpl implements  ProjectService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired 
	private CityRepository cityRepository;
	
	@Autowired 
	private ClientRepository clientRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired 
	private ProjectDesignRepository projectDesignRepository;
	
	@Override
	public ResultJson findAllByPage(String role_code,Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		Page<Project> projectList = null;
		if( role_code.substring(0, 3).equals("xhb")) {
			Region region = regionRepository.findByCode( role_code.substring(0, 4));
			projectList = projectRepository.findByRegion(region, pageable);
		}else {
			projectList = projectRepository.findAll(pageable);
		}
		resultJson.setData(projectList);
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Project project = projectRepository.findOne(id);
		if(project == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		String oldCity = project.getOldCity();
		
		resultMap.put("oldCity", oldCity);
		resultMap.put("project", project);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(Project project) {
		ResultJson resultJson = new ResultJson();
		
		/**
		 * 手动set关联实体
		 */
		if(project.getCity() !=null) {
			City city  = cityRepository.findOne(project.getCity().getId());
			if(city == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			project.setCity(city);
		}
		if(project.getClient()!=null) {
			Client client = clientRepository.findOne(project.getClient().getId());
			if(client == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			project.setClient(client);
		}
		if(project.getRegion()!=null) {
			Region region = regionRepository.findOne(project.getRegion().getId());
			if(region == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			project.setRegion(region);
		}
		
		/**
		 * 获得项目设计要素实体数组，手动循环设置项目外键
		 */
		List<ProjectDesign> projectDesigns = project.getProjectDesigns();
		if(!projectDesigns.isEmpty()) {
			for( int i = 0 ; i < projectDesigns.size() ; i++) {
				ProjectDesign result = projectDesigns.get(i);
				result.setProject(project);
				projectDesignRepository.save(result);
			}
		}

		//保存
		projectRepository.save(project);
		
		resultJson.setSuccess(true);		
		resultJson.setData(project);	
		LOGGER.info("创建实体：" + project.toString());
		return resultJson;
	}

	@Override
	public ResultJson delete(Long[] ids) {
		ResultJson resultJson = new ResultJson();
		
		for( int i = 0 ; i < ids.length; i++) {
			Project project = projectRepository.findOne(ids[i]);
			if(project == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			projectRepository.delete(project);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public ResultJson search(String role_code,String name,Date firstEntry,Date firstEntry1,String archFormat,String communityType,String contractMode,Long region_id,Long city_id,String client_name, Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		
		Page<Project> projectList = projectRepository.findAll(new Specification<Project>(){
			@Override
			public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 
				if(role_code.substring(0, 3).equals("xhb")){
					Region region = regionRepository.findByCode(role_code.substring(0, 4));
	            		Join<Region,Project> join = root.join("region", JoinType.INNER);
	            		predicates.add(cb.equal(join.get("id").as(Long.class),  region.getId()));
	            }
				if(null != name){
                    predicates.add(cb.like(root.get("name"), "%"+name+"%"));
                }
                if(null != firstEntry){
                    predicates.add(cb.between(root.get("firstEntry"), firstEntry,firstEntry1));
                }
                if(null != archFormat){
                    predicates.add(cb.like(root.get("archFormat"), "%"+archFormat+"%"));
                }
                if(null != communityType){
                    predicates.add(cb.like(root.get("communityType"), "%"+communityType+"%"));
                }
                if(null != contractMode){
                    predicates.add(cb.like(root.get("contractMode"), "%"+contractMode+"%"));
                }
                if(null != region_id){
                		Join<Region,Project> join = root.join("region", JoinType.INNER);
                		predicates.add(cb.equal(join.get("id").as(Long.class),  region_id));
                }
                if(null != city_id){
	            		Join<City,Project> join = root.join("city", JoinType.INNER);
	            		predicates.add(cb.equal(join.get("id").as(Long.class),  city_id));
	            }
                if(null != client_name){
                		predicates.add(cb.like(root.get("client").get("name").as(String.class), "%"+client_name+"%"));
	            }
				
			    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(projectList);
		LOGGER.info("数据查询");
		return resultJson;
	}
}
