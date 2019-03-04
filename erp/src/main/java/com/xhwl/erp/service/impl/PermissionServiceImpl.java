package com.xhwl.erp.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.domain.PermissionRepository;
import com.xhwl.erp.domain.RolePermissionRepository;
import com.xhwl.erp.domain.RoleRepository;
import com.xhwl.erp.entity.Role;
import com.xhwl.erp.entity.RolePermission;
import com.xhwl.erp.service.PermissionService;
import com.xhwl.erp.util.result.ResultJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限获取
 * @author jiayiwu
 * @date 2018年3月21日
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
	public ResultJson findAll() {
		ResultJson resultJson = new ResultJson();
		Number pId =0;
		String menuName=null,permissionName=null;
		List<Object> menuList  = new ArrayList<>();
		List<Object> permissionList  = new ArrayList<>();
		JSONObject returnData = new JSONObject();
		List<Object[]> allPermissions = permissionRepository.findAllPermission();
		//获取菜单元素
		for (Object[] permission : allPermissions) {
			Object[] permissionArrary = (Object[]) permission;
			if(menuList.indexOf(permissionArrary[1])==-1) {//去掉重复元素
	    			menuList.add(permissionArrary[1]);
	    		}
		}
		//获取相同菜单的权限元素
		for (Object menu : menuList) {
			List<Object> resulList1  = new ArrayList<>();
			JSONObject returnData1 = new JSONObject();
			for (Object[] permission : allPermissions) {
				Object[] permissionArrary = (Object[]) permission;
				JSONObject returnData2 = new JSONObject();
				pId = (Number) permissionArrary[0];
				menuName = (String) permissionArrary[1];
				permissionName = (String) permissionArrary[2];
				if(menu.equals(menuName)) {
					returnData2.put("id", pId);
					returnData2.put("permissionName", permissionName);
					resulList1.add(returnData2);
				}
			}
			returnData1.put("permissions", resulList1);
			returnData1.put("menuName", menu);
			permissionList.add(returnData1);
		}
		returnData.put("List", permissionList);
		resultJson.setData(returnData);
		resultJson.setSuccess(true);
		return resultJson;
	}
    
    @Override
	public ResultJson listRolePermission(int page, int size) {
		ResultJson resultJson = new ResultJson();
		List<Object> roleList  = new ArrayList<>();
		List<Object> resultList  = new ArrayList<>();
		JSONObject returnData = new JSONObject();
		
		List<Object[]> rolePermissions = permissionRepository.listRolePermission(size,page*size);
		int totalCount = permissionRepository.rolePermissionTotalCount();
		/****************合并相同角色*********************/
		for (Object[] rolePermission : rolePermissions) {
			Object[] rolePermissionArrary = (Object[]) rolePermission;
			if(roleList.indexOf(rolePermissionArrary[1])==-1) {//去掉重复元素
				roleList.add(rolePermissionArrary[1]);
	    		}
		}
		//获取相同的菜单权限元素
		for (Object object : roleList) {
			Number permissionId=0;
			String roleName=null,menuName=null,menuCode=null,permissionName=null;
			List<Object> resulList1  = new ArrayList<>();
			List<Object> menuList  = new ArrayList<>();
			JSONObject returnData1 = new JSONObject();
			for (Object[] rolePermission : rolePermissions) {
				Object[] rolePermissionArrary = (Object[]) rolePermission;
				JSONObject returnData2 = new JSONObject();
				roleName = (String) rolePermissionArrary[1];
				permissionId = (Number) rolePermissionArrary[2];
				menuCode = (String) rolePermissionArrary[3];
				menuName = (String) rolePermissionArrary[4];
				permissionName = (String) rolePermissionArrary[5];
				
				if(object.equals(roleName)) {
					returnData2.put("permissionId", permissionId);
					returnData2.put("menuName", menuName);
					returnData2.put("menuCode", menuCode);
					returnData2.put("permissionName", permissionName);
					resulList1.add(returnData2);
					menuList.add(menuName);
				}
			}
			
			/******************合并相同菜单*******************/
			List<Object> menu2List  = new ArrayList<>();
			for (Object object2 : menuList) {
				if(menu2List.indexOf(object2)==-1) {//去掉重复元素
					menu2List.add(object2);
		    		}
			}
			
			List<Object> resulList2  = new ArrayList<>();
			for (Object menu2 : menu2List) {
				List<Object> permissionList2  = new ArrayList<>();
				JSONObject returnData3 = new JSONObject();
				for (Object resultObj : resulList1) {
					JSONObject resultData = (JSONObject)resultObj;
					JSONObject returnData2 = new JSONObject();
					String menu_name = (String) resultData.get("menuName");
					if(menu_name!=null && menu2.equals(menu_name)) {
						returnData2.put("permissionId", resultData.get("permissionId"));
						returnData2.put("permissionName", resultData.get("permissionName"));
						permissionList2.add(returnData2);
					}
				}
				returnData3.put("permissions", permissionList2);
				returnData3.put("menuName", menu2);
				resulList2.add(returnData3);
			}
			
			/******************合并相同菜单 end****************/
			
			Role role = roleRepository.findByName((String)object);
			returnData1.put("menus", resulList2);
			returnData1.put("roleId", role.getId());
			returnData1.put("roleName", object);
			resultList.add(returnData1);
		}
		/******************合并相同角色 end****************/
		returnData.put("List", resultList);
		returnData.put("totalCount", totalCount);
		resultJson.setData(returnData);
		resultJson.setSuccess(true);
		return resultJson;
	}

    @Override
	public ResultJson insertRolePermission(JSONObject jsonObject) {
    		ResultJson resultJson = new ResultJson();
    		List<RolePermission> arrary = new ArrayList<>();
    	    Long role_id = jsonObject.getLong("role_id");
    	    JSONArray permissionList = jsonObject.getJSONArray("permissions");
    	    
    	    List<RolePermission> oldResult = rolePermissionRepository.getRolePermission(role_id);
    	    if(oldResult !=null && oldResult.size()!=0) {//如果非空，循环删除已存在的权限数组
    			for(RolePermission rolePermission :oldResult) {
    				rolePermissionRepository.delete(rolePermission);
    			}
    		}
    	    
    	    for (Object object : permissionList) {//循环保存新权限数组
    	    		JSONObject obj = (JSONObject)object;
    	    		RolePermission newResult = new RolePermission();	
    	    		newResult.setPermission_id(obj.getLong("p_id"));
    	    		newResult.setRole_id(role_id);
    	    		rolePermissionRepository.save(newResult);
    	    		arrary.add(newResult);
		}
		
		resultJson.setSuccess(true);
		resultJson.setData(arrary);
		return resultJson;
	}
    
    @Override
  	public ResultJson delRolePermission(Long role_id) {
      		ResultJson resultJson = new ResultJson();
      	    List<RolePermission> oldResult = rolePermissionRepository.getRolePermission(role_id);
      	    if(oldResult !=null && oldResult.size()!=0) {//如果非空，循环删除已存在的权限数组
      			for(RolePermission rolePermission :oldResult) {
      				rolePermissionRepository.delete(rolePermission);
      			}
      		}
  		
  		resultJson.setSuccess(true);
  		return resultJson;
  	}
    
    /**
     * 查询某用户的 角色  菜单列表   权限列表
     *
     * @param username
     * @return
     */
    @Override
    public JSONObject getUserPermission(String username) {
    		JSONObject userPermission = getUserPermissionFromDB(username);
        return userPermission;
    }

    /**
     * 从数据库查询用户权限信息
     *
     * @param username
     * @return
     */
    private JSONObject getUserPermissionFromDB(String username) {
    		JSONObject returnData = new JSONObject();
    		List<Object> menuList  = new ArrayList<>();
    		List<Object> permissionList  = new ArrayList<>();
    		Number accountId =0,roleId= 0;
    		String accountName=null,roleName=null,roleCode=null,userName=null,userCode=null;
    		/**
    		 * 获取用户权限
    		 */
    		List<Object[]> userPermissionList = permissionRepository.getUserPermission(username);
        for (Object userPermission : userPermissionList) {
        		Object[] userPermissionArrary = (Object[]) userPermission;
        		
        		accountId = (Number) userPermissionArrary[0];
        		accountName = (String) userPermissionArrary[1];
        		roleId = (Number) userPermissionArrary[2];
        		roleName = (String) userPermissionArrary[3];
        		roleCode = (String) userPermissionArrary[4];
        		userName = (String) userPermissionArrary[5];
        		userCode = (String) userPermissionArrary[6];
        		if(menuList.indexOf(userPermissionArrary[7])==-1) {//去掉重复元素
        			menuList.add(userPermissionArrary[7]);
        		}
        		permissionList.add(userPermissionArrary[8]);
        }
        
        returnData.put("accountId", accountId);
        returnData.put("accountName", accountName);
        returnData.put("roleId", roleId);
        returnData.put("roleName", roleName);
        returnData.put("roleCode", roleCode);
        returnData.put("userName", userName);
        returnData.put("userCode", userCode);
        returnData.put("menuList", menuList);
        returnData.put("permissionList", permissionList);
        
        /**
         * 管理员获取所有菜单、权限
         */
        String adminRoleCode = "admin";
        if (adminRoleCode.equals(roleCode)) {
            //查询所有菜单  所有权限
    			List<String> adminMenuList1  = new ArrayList<>();
        		List<String> adminMenuList = permissionRepository.getAllMenu();
        		List<String> adminPermissionList = permissionRepository.getAllPermission();
                /**
                 * 去掉数据重复元素
                 */
            for (int i = 0; i < adminMenuList.size(); i++) {
        	        	if(adminMenuList1.indexOf(adminMenuList.get(i))==-1){
        	        		adminMenuList1.add(adminMenuList.get(i));
        	         }
        		}
            returnData.put("menuList", adminMenuList1);
            returnData.put("permissionList", adminPermissionList);
        }
        
        return returnData;
    }
}
