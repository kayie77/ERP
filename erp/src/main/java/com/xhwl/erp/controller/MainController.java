package com.xhwl.erp.controller;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.domain.UserRepository;
import com.xhwl.erp.entity.Account;
import com.xhwl.erp.service.AccountService;
import com.xhwl.erp.service.LoginService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.MD5Util;

@Controller  
@EnableAutoConfiguration
@RequestMapping("/shiro")
@Api(description="主接口")
public class MainController {
		
	@Autowired
    AccountService accountService;
	
	@Autowired
    UserRepository userRepository;
	
	/***********************shiro 权限控制**********************************/
	
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * @author jiayiwu
     * @date 2018年3月22日
     * @param requestJson
     * @return
     */
    @PostMapping("/auth")
    public @ResponseBody ResultJson authLogin(@RequestBody JSONObject requestJson) {
		return loginService.authLogin(requestJson);
    }
    
    /**
     * 查询当前登录用户的信息
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
    @PostMapping("/getInfo")
    public @ResponseBody JSONObject getInfo() {
        return loginService.getInfo();
    }

    /**
     * 退出登录
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
    @PostMapping("/logout")
    public @ResponseBody ResultJson logout() {
        return loginService.logout();
    }
    
    /***********************旧版接口 弃用**********************************/
    
    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "currAccount";
    
	/**
	 * vue 框架入口
	 * @author jiayiwu
	 * @date 2017年12月26日
	 * @return
	 */
	@GetMapping("/index")
	@ApiIgnore
    public String index() {//@SessionAttribute(SESSION_KEY) Account account,Model model
//		model.addAttribute(account);
        return "index";
    }
	
	/**
	 * 注册
	 * @author jiayiwu
	 * @date 2017年12月26日
	 * @param account
	 * @param request
	 * @param session
	 * @return
	 * @throws AppWebException 
	 */
	@PostMapping("/register")
	@ApiIgnore
    public @ResponseBody ResultJson register(@RequestBody Account account, HttpServletRequest request,  HttpSession session) {
		
		ResultJson resultJson = accountService.register(account);//注册用户
		session.setAttribute(SESSION_KEY, account);//写入session缓存
        return resultJson;
    }
	
	/**
	 * 登录
	 * @author jiayiwu
	 * @date 2017年12月26日
	 * @param request
	 * @param session
	 * @return
	 * @throws AppWebException 
	 */
	@PostMapping("/login")
	@ApiIgnore
    public @ResponseBody ResultJson login(@RequestParam String name,@RequestParam String password, HttpSession session) {
		ResultJson resultJson = new ResultJson();
		Account account = accountService.login(name, MD5Util.encode(password));//登录
        session.setAttribute(SESSION_KEY, account);//写入session
		resultJson.setSuccess(true);
		resultJson.setData(userRepository.findByAccount(account));
        return resultJson;
    }

	/**
	 * test
	 * @author jiayiwu
	 * @date 2017年12月27日
	 * @param session
	 * @return
	 */
	@GetMapping("/getSession")
	@ApiIgnore
    public @ResponseBody Account getSession(HttpSession session){
		Account account =  (Account)session.getAttribute(SESSION_KEY);
       return account;
    }
}
