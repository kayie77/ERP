package com.xhwl.erp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.RolePermissionRepository;
import com.xhwl.erp.domain.RoleRepository;
import com.xhwl.erp.entity.Role;
import com.xhwl.erp.entity.RolePermission;
import com.xhwl.erp.service.RoleService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class RoleServiceImpl implements  RoleService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	 @Autowired
	   private RoleRepository  roleRepository;
	 
	 @Autowired
	    private RolePermissionRepository rolePermissionRepository;
	 
	 @Override
		public ResultJson findAll() {
			
			ResultJson resultJson = new ResultJson();
			resultJson.setData(roleRepository.findAll());
			resultJson.setSuccess(true);
			return resultJson;
		}
	 
	 @Override
		public ResultJson findAllByPage(Pageable pageable) {
			
			ResultJson resultJson = new ResultJson();
			resultJson.setData(roleRepository.findAll(pageable));
			resultJson.setSuccess(true);
			
			LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
			return resultJson;
		}
	 
	 @Override
		public ResultJson findUpdateData(Long id) {
			
			ResultJson resultJson = new ResultJson();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			    
			Role role = roleRepository.findOne(id);
			if(role == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			
			resultMap.put("role", role);
			resultJson.setData(resultMap);
			resultJson.setSuccess(true);
			LOGGER.info("获取更新实体时所需数据");
			return resultJson;
		}
	 
		@Override
		public ResultJson save(Role role) {
			ResultJson resultJson = new ResultJson();
			
			resultJson.setSuccess(true);		
			resultJson.setData(roleRepository.save(role));
			LOGGER.info("创建实体");
			return resultJson;
		}
		
		@Override
		public ResultJson delete(Long[] ids) {
			
			ResultJson resultJson = new ResultJson();
			//循环删除
			for( int i = 0 ; i < ids.length; i++) {
				Role role = roleRepository.findOne(ids[i]);
				if(role == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
				roleRepository.delete(role);
				//删除对应的角色权限
				List<RolePermission> rolePermissions = rolePermissionRepository.getRolePermission(role.getId());
	      	    if(rolePermissions !=null && rolePermissions.size()!=0) {//如果非空，循环删除已存在的权限数组
	      			for(RolePermission rolePermission :rolePermissions) {
	      				rolePermissionRepository.delete(rolePermission);
	      			}
	      		}
			}
			
			resultJson.setSuccess(true);
			LOGGER.info("删除实体");
			return resultJson;
			
		}

}
