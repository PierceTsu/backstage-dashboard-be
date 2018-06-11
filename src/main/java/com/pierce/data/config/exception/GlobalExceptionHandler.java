package com.pierce.data.config.exception;

import com.pierce.data.common.ResponseCode;
import com.pierce.data.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Project : data
 * @Package Name : com.pierce.data.config.exception
 * @Description: 全局异常处理
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-04 18:28
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ServerResponse defaultErrorHandler(Exception e) {
        log.error("exception: {}", e);
        return ServerResponse.createByErrorCodeMsg(ResponseCode.DEFAULT_ERROR.getCode(), ResponseCode.DEFAULT_ERROR.getDesc());
    }

    /**
     * 数据校验失败统一处理
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ServerResponse bindExceptionHandler(BindException bindException) {
        FieldError fieldError = bindException.getFieldError();
        log.info("参数校验失败: {}", fieldError.getDefaultMessage());
        return ServerResponse.createByErrorMsg(fieldError.getDefaultMessage());
    }

    /**
     * GET/POST请求方法错误的拦截器
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ServerResponse httpRequestMethodHandler() {
        return ServerResponse.createByErrorCodeMsg(ResponseCode.METHOD_NOT_SUPPORT.getCode(), ResponseCode.METHOD_NOT_SUPPORT.getDesc());
    }

    /**
     * 权限不足报错拦截
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ServerResponse unauthorizedExceptionHandler() {
        return ServerResponse.createByErrorCodeMsg(ResponseCode.PERMISSION_DENIED.getCode(), ResponseCode.PERMISSION_DENIED.getDesc());
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,而连登录都还没登录的时候,会报此错
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public ServerResponse unauthenticatedException() {
        return ServerResponse.createByErrorCodeMsg(ResponseCode.LOGIN_OVERDUE.getCode(), ResponseCode.LOGIN_OVERDUE.getDesc());
    }
}
