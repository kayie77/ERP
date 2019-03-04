package com.xhwl.erp.controller;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.service.LoginService;
import com.xhwl.erp.util.result.ResultJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * shiro 登录测试
 * @author jiayiwu
 * @date 2018年3月20日
 */
@RestController
@RequestMapping("/logintest")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param requestJson
     * @return
     */
    @PostMapping("/auth")
    public ResultJson authLogin(@RequestBody JSONObject requestJson) {
        return loginService.authLogin(requestJson);
    }

    /**
     * 查询当前登录用户的信息
     *
     * @return
     */
    @PostMapping("/getInfo")
    public JSONObject getInfo() {
        return loginService.getInfo();
    }

    /**
     * 登出
     *
     * @return
     */
    @PostMapping("/logout")
    public ResultJson logout() {
        return loginService.logout();
    }
}
