package com.xhwl.erp.util.exception;

import java.io.IOException;
import java.net.ConnectException;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.xhwl.erp.util.file.StorageFileNotFoundException;
import com.xhwl.erp.util.result.ResultJson;

/**
 * 全局异常配置
 * @author jiayiwu
 * @date 2017年12月25日
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	protected static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	//运行时异常
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultJson runtimeExceptionHandler(RuntimeException runtimeException) {
        return result(ErrorConstant.RUNTIME_EXCEPTION.getCode(), ErrorConstant.RUNTIME_EXCEPTION.getMsg(), runtimeException);
    }
    
    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResultJson nullPointerExceptionHandler(NullPointerException ex) {
        return result(ErrorConstant.NULL_POINTER_EXCEPTION.getCode(), ErrorConstant.NULL_POINTER_EXCEPTION.getMsg(), ex);
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public ResultJson classCastExceptionHandler(ClassCastException ex) {
        return result(ErrorConstant.CLASS_CAST_EXCEPTION.getCode(), ErrorConstant.CLASS_CAST_EXCEPTION.getMsg(), ex);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public ResultJson iOExceptionHandler(IOException ex) {
        return result(ErrorConstant.IO_EXCEPTION.getCode(), ErrorConstant.IO_EXCEPTION.getMsg(), ex);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public ResultJson noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return result(ErrorConstant.NO_SUCH_METHOD_EXCEPTION.getCode(), ErrorConstant.NO_SUCH_METHOD_EXCEPTION.getMsg(), ex);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public ResultJson indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return result(ErrorConstant.INDEX_OUT_OF_BOUNDS_EXCEPTION.getCode(), ErrorConstant.INDEX_OUT_OF_BOUNDS_EXCEPTION.getMsg(), ex);
    }

    //网络异常
    @ExceptionHandler(ConnectException.class)
    @ResponseBody
    public ResultJson connectException(ConnectException ex) {
        return result(ErrorConstant.CONNECT_EXCEPTION.getCode(), ErrorConstant.CONNECT_EXCEPTION.getMsg(), ex);
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ResultJson requestNotReadable(HttpMessageNotReadableException ex) {
        return result(ErrorConstant.BAD_REQUEST.getCode(), ErrorConstant.BAD_REQUEST.getMsg(), ex);
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public ResultJson requestTypeMismatch(TypeMismatchException ex) {
        return result(ErrorConstant.BAD_REQUEST.getCode(), ErrorConstant.BAD_REQUEST.getMsg(), ex);
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ResultJson requestMissingServletRequest(MissingServletRequestParameterException ex) {
        return result(ErrorConstant.BAD_REQUEST.getCode(), ErrorConstant.BAD_REQUEST.getMsg(), ex);
    }
    
    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public ResultJson request405(HttpRequestMethodNotSupportedException ex) {
        return result(ErrorConstant.METHOD_NOT_ALLOWED.getCode(), ErrorConstant.METHOD_NOT_ALLOWED.getMsg(), ex);
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public ResultJson request406(HttpMediaTypeNotAcceptableException ex) {
        return result(ErrorConstant.NOT_ACCEPTABLE.getCode(), ErrorConstant.NOT_ACCEPTABLE.getMsg(), ex);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseBody
    public ResultJson server500(RuntimeException runtimeException) {
        return result(ErrorConstant.INTERNAL_SERVER_ERROR.getCode(), ErrorConstant.INTERNAL_SERVER_ERROR.getMsg(), runtimeException);
    }

    //自定义错误
    @ExceptionHandler({AppWebException.class})
    @ResponseBody
    public ResultJson appWebException(AppWebException appWebException) {
        return result(appWebException.getErrCode(), appWebException.getMessage(), appWebException);
    }

    //json转换错误
    @ExceptionHandler({JsonMappingException.class})
    @ResponseBody
    public ResultJson jsonMappingException(JsonMappingException jsonMappingException) {
        return result(ErrorConstant.ERROR_FORMAT_PARAMETER.getCode(), ErrorConstant.ERROR_FORMAT_PARAMETER.getMsg(), jsonMappingException);
    }
    
    //shiro异常
    @ExceptionHandler({ShiroException.class})
    @ResponseBody
    public ResultJson shiroException(ShiroException shiroException) {
        return result(ErrorConstant.NOT_AUTHORIZATION.getCode(), shiroException.getMessage(), shiroException);
    }
    
    //权限不足报错拦截
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseBody
    public ResultJson unauthorizedException(ShiroException shiroException) {
        return result(ErrorConstant.NOT_AUTHORIZATION.getCode(), ErrorConstant.NOT_AUTHORIZATION.getMsg(), shiroException);
    }
    
    //捕捉文件上传异常捕捉
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exception){
	     return ResponseEntity.notFound().build();
	}
    
    /**
     * 结果集
     * @param errCode
     * @param errMsg
     * @param e
     * @return
     */
    private ResultJson result(int errCode, String errMsg, Exception e){
        ResultJson resultJson= new ResultJson();
        resultJson.setErrCode(errCode);
        resultJson.setErrMsg(errMsg);

        logException(e);

        return resultJson;
    }
   
    /**
     * 异常记录
     * @param e
     */
    private void logException(Exception e){
        if(e instanceof  AppWebException){
            LOGGER.warn(e.getMessage(), e);
        }else{
            LOGGER.error(e.getMessage(), e);
        }
    }
    

}
