package com.xhwl.erp.util.exception;

/**
 * 错误对照码
 * @author jiayiwu
 * @date 2017年12月25日
 */
public enum ErrorConstant {
	

	/**
	 * 客户端错误
	 */
    BAD_REQUEST(400, "请求参数有误"),
    NOT_AUTHORIZATION(401, "权限不足"),
    METHOD_NOT_ALLOWED(405, "请求方式有误"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    
	/**
	 * 自定义用户类错误
	 */
    DATA_NOTEXIT(100, "数据不存在"),
    
    ACCOUNT_EXIT(101, "该账户已存在"),
    ACCOUNT_NOTEXIT(102, "该账户不存在，请重新输入"),
    ACCOUNT_NOTLOGIN(103, "用户未登录"),
    ACCOUNT_OVERDUE(104, "登录已过期,请重新登录"),
    
    FOREIGN_KEY_NOTEXIT(110, "该外键实体不存在"),
    
	/**
	 * 服务器错误
	 */
    RUNTIME_EXCEPTION(1000, "[服务器]运行时异常"),
    NULL_POINTER_EXCEPTION(1001, "[服务器]空值异常"),
    CLASS_CAST_EXCEPTION(1002, "[服务器]数据类型转换异常"),
    IO_EXCEPTION(1003, "[服务器]IO异常"),
    NO_SUCH_METHOD_EXCEPTION(1004, "[服务器]未知方法异常"),
    INDEX_OUT_OF_BOUNDS_EXCEPTION(1005, "[服务器]数组越界异常"),
    CONNECT_EXCEPTION(1006, "[服务器]网络异常"),
    ERROR_MEDIA_TYPE(1007, "[服务器]Content-type错误，请使用application/json"),
    EMPTY_REQUEST_BOYD(1008, "[服务器]request请求body不能为空"),
    ERROR_REQUEST_BOYD(1009, "[服务器]request请求body非json对象"),
    ERROR_VERSION(2000, "[服务器]版本号错误"),
    ERROR_FORMAT_PARAMETER(2001, "[服务器]参数格式错误");
    
	private final int code;
    private final String msg;
    
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
    
	ErrorConstant(int code, String msg) {
	    this.code = code;
	    this.msg = msg;
	}
	
	public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ErrorConstant constant : ErrorConstant.values()) {
                if (constant.code == value) {
                    return constant.msg;
                }
            }
        }
        return "";
    }

    public ErrorConstant getTypeByValue(int value) {
        for (ErrorConstant constant : ErrorConstant.values()) {
            if (constant.code == value) {
                return constant;
            }
        }
        return null;
    }

}
