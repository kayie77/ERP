package com.xhwl.erp.util.exception;

/**
 * 自定义错误对象
 * @author jiayiwu
 * @date 2017年12月25日
 */
public class AppWebException extends RuntimeException{

	private static final long serialVersionUID = -8198281171334131008L;
	
	/**
	 * 错误码
	 */
	 private int errCode;
	 
	 public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public AppWebException(int errCode, String message,Exception exception) {
		 super(message, exception);
		 this.errCode = errCode;
	}
	 
	public AppWebException(int errCode, String message) {
	     this(errCode, message,null);
	 }
	 
	 
}
