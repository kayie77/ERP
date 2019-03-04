package com.xhwl.erp.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.ProjectReformDetaileRepository;
import com.xhwl.erp.domain.ProjectReformRepository;
import com.xhwl.erp.domain.ProjectRepository;
import com.xhwl.erp.entity.Project;
import com.xhwl.erp.entity.ProjectReform;
import com.xhwl.erp.entity.ProjectReformDetaile;
import com.xhwl.erp.service.ProjectReformService;

import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.ArithUtil;


@Service
public class ProjectReformServiceImpl implements  ProjectReformService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectReformServiceImpl.class);
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired 
	private ProjectReformRepository projectReformRepository;
	
	@Autowired 
	private ProjectReformDetaileRepository projectReformDetaileRepository;
	
	@Override
	public ResultJson findByProject(Long p_id) {
		
		ResultJson resultJson = new ResultJson();
		Project project = projectRepository.findOne(p_id);//获得项目信息
		if(project == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());

		resultJson.setSuccess(true);
		resultJson.setData(projectReformRepository.findByProject(project));
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ProjectReform projectReform = projectReformRepository.findOne(id);
		if(projectReform == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());

		resultMap.put("projectReform", projectReform);

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson findDataByYear(String year,Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Object> project_year_list  = new ArrayList<>();
		List<Object> resultList  = new ArrayList<>();
		Page<BigInteger> projectIdPage = projectRepository.getIdArrary(pageable);
		List<BigInteger> projectIdList= projectIdPage.getContent();
		List<ProjectReform> projectReformList = projectReformRepository.findByYear(year);//获得经过年份过滤的项目改造信息
				
		if(!projectReformList.isEmpty()) {//获得经过年份筛选的项目信息
			for( int i = 0 ; i < projectReformList.size() ; i++) {
				Long pid = projectReformList.get(i).getProject().getId();
				project_year_list.add(pid);
			}
		}
		
		for(int j = 0 ; j < projectIdList.size() ; j++) {//循环所有项目信息，不是该年份的所有项目改造信息置空。是的话则返回该项目信息和该年份改造信息
			BigInteger pid = projectIdList.get(j);
			Project project= projectRepository.findOne(pid.longValue());
			if(project == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			if(project_year_list.contains(pid)) {
				List<ProjectReform> oldList = project.getProjectReforms();
				List<ProjectReform> newList  = new ArrayList<ProjectReform>();
				for( int k = 0 ; k < oldList.size() ; k++) {
					ProjectReform projectReform = oldList.get(k);
					if(projectReform.getYear().equals(year)) {
						newList.add(projectReform);
					}
				}
				project.setProjectReforms(null);//置空项目改造信息
				project.setProjectReforms(newList);//放入经过年份筛选的项目改造信息	
				resultList.add(project);//得到经过年份筛选的项目信息
			}else {
				List<ProjectReform> newList  = new ArrayList<ProjectReform>();
				project.setProjectReforms(newList);//置空项目改造信息
				resultList.add(project);//得到经过年份筛选的项目信息
			}
		}
		
		resultMap.put("content", resultList);
		resultMap.put("totalElements", projectIdPage.getTotalElements());
		resultMap.put("totalPages", projectIdPage.getTotalPages());
		resultMap.put("size", projectIdPage.getSize());
		resultMap.put("number", projectIdPage.getNumber());
		resultMap.put("sort", projectIdPage.getSort());
		resultMap.put("numberOfElements", projectIdPage.getNumberOfElements());

		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(Long p_id,ProjectReform projectReform) {
		ResultJson resultJson = new ResultJson();
		double totalAmount = 0;
		
		/**
		 * 手动set关联实体
		 */
		if(p_id !=null) {
			Project project  = projectRepository.findOne(p_id);
			if(project == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			projectReform.setProject(project);
		}

		/**
		 * 获得项目设计要素实体数组，手动循环设置项目外键
		 */
		List<ProjectReformDetaile> projectReformDetailes = projectReform.getProjectReformDetailes();
		if(!projectReformDetailes.isEmpty()) {
			for( int i = 0 ; i < projectReformDetailes.size() ; i++) {
				ProjectReformDetaile result = projectReformDetailes.get(i);
				totalAmount  = ArithUtil.add(totalAmount, result.getAmount());
				result.setProjectReform(projectReform);
				projectReformDetaileRepository.save(result);
			}
		}

		//保存
		projectReform.setAmount(totalAmount);//统计总金额
		projectReformRepository.save(projectReform);
		
		resultJson.setSuccess(true);		
		resultJson.setData(projectReform);	
		LOGGER.info("创建实体：" + projectReform.toString());
		return resultJson;
	}

	@Override
	public ResultJson delete(Long[] ids) {
		ResultJson resultJson = new ResultJson();
		
		for( int i = 0 ; i < ids.length; i++) {
			ProjectReform projectReform = projectReformRepository.findOne(ids[i]);
			if(projectReform == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			projectReformRepository.delete(projectReform);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
}
