package com.pierce.data.common;

/**
 * @Project : data
 * @Package Name : com.pierce.data.common
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-01 15:24
 */
public enum ResponseCode {

    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    LOGIN_OVERDUE(11, "登录过期, 请重新登录"),
    NOT_FOUND(404, "NOT_FOUND"),
    METHOD_NOT_SUPPORT(405, "METHOD_NOT_SUPPORT"),
    DEFAULT_ERROR(500, "未知错误"),
    PERMISSION_DENIED(502, "权限不足"),
    ILLEGAL_ARGS(2, "ILLEGAL_ARGS");


    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
