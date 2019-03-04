package com.xhwl.erp.util.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpSessionUtils {
	

    private static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }
    
    private static HttpSession getSession() {
        return getRequest().getSession();
    }
    
    /**
     * 存放会话级别的值
     * @author jiayiwu
     * @date 2017年12月25日
     * @param requestKey
     * @param obj
     */
    public static void putObject(String requestKey, Object obj) {
    		getRequest().setAttribute(requestKey, obj);
    }
    
    /**
     * 根据 session key获取会话值
     * @author jiayiwu
     * @date 2017年12月25日
     * @param requestKey
     * @return
     */
    public static <T> T  getObject(String requestKey){
        return  (T)getRequest().getAttribute(requestKey);
    }
    
    public static Long getCurrentAppUserId() {
    		Object object = getRequest().getAttribute(AuthSessionUtils.WEB_CURRENT_USER_ID);
    		if(object != null) return (Long)object;
    		 
    		return null;
    }
    
    /**
     * 获取当前用户token
     * @author jiayiwu
     * @date 2017年12月25日
     * @return
     */
    public static String getCurrentAppUserToken() {
        Object object = getRequest().getAttribute(AuthSessionUtils.WEB_CURRENT_USER_TOKEN);
        if(object != null) return (String)object;

        return null;
    }

}
