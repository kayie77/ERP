package com.xhwl.erp.util.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResultJson implements Serializable {
	
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4227833010077730477L;
    
    /**
     * 是否成功，默认失败
     */
    private boolean success = false;
    
    /**
     * 返回消息
     */
    private String errMsg;

    /**
     * 返回CODE
     */
    private int errCode;
    
    /**
     * 返回结果封装器
     */
    private Object data;
    
    /**
     * 返回键值对
     */
    @JsonIgnore
    private Map<String, Object> resultMap;
    
    public Map<String, Object> getResultMap() {
    	if (resultMap == null) resultMap = new HashMap<String, Object>();
		return resultMap;
	}

	public boolean isSuccess() {
        return success;
    }

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public Object getData() {
		if (resultMap != null) return resultMap;
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
 
}
