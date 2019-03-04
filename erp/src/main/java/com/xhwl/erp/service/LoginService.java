package com.xhwl.erp.service;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.entity.Account;
import com.xhwl.erp.util.result.ResultJson;

/**
 * @author: hxy
 * @description: 登录Service
 * @date: 2017/10/24 11:02
 */
public interface LoginService {
    /**
     * 登录表单提交
     *
     * @param jsonObject
     * @return
     */
	ResultJson authLogin(JSONObject jsonObject);

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
	Account getAccount(String username, String password);

    /**
     * 查询当前登录用户的权限等信息
     *
     * @return
     */
	JSONObject getInfo();

    /**
     * 退出登录
     *
     * @return
     */
	ResultJson logout();

}
