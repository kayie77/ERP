package com.xhwl.erp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.service.LoginService;
import com.xhwl.erp.service.PermissionService;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.Constants;
import com.xhwl.erp.util.util.MD5Util;
import com.xhwl.erp.domain.AccountRepository;
import com.xhwl.erp.entity.Account;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: hxy
 * @description: 登录service实现类
 * @date: 2017/10/24 11:53
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PermissionService permissionService;

    /**
     * 登录表单提交
     *
     * @param jsonObject
     * @return
     */
    @Override
    public ResultJson authLogin(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        ResultJson resultJson = new ResultJson();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Util.encode(password));
        try {
            currentUser.login(token);
            resultJson.setSuccess(true);
            resultJson.setData(getInfo());
        } catch (AuthenticationException e) {
        		resultJson.setSuccess(false);
        		resultJson.setErrMsg("登录失败");
        }
        
        return resultJson;
    }

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public Account getAccount(String username, String password) {
        return accountRepository.findByNameAndPassword(username, password);
    }

    /**
     * 查询当前登录用户的权限等信息
     *
     * @return
     */
    @Override
    public JSONObject getInfo() {
    	    JSONObject  jsonObject = new JSONObject();
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Constants.SESSION_USER_INFO);
        //获取sessionID值
        String sessionID = (String) session.getId();
       //根据用户名称获取用户权限
        String username = account.getName();
        JSONObject userPermission = permissionService.getUserPermission(username);
        //把用户权限放入session中
        session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
        
        jsonObject.put("sessionID", sessionID);
        jsonObject.put("userPermission", userPermission);
        return jsonObject;
    }

    /**
     * 退出登录
     *
     * @return
     */
    @Override
    public ResultJson logout() {
    		ResultJson resultJson = new ResultJson();
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
            resultJson.setSuccess(true);
        } catch (Exception e) {
        		resultJson.setSuccess(false);
        }
        return resultJson;
    }
    
}
