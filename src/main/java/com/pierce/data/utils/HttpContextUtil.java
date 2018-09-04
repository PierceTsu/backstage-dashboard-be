package com.pierce.data.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Project : data
 * @Package Name : com.pierce.data.utils
 * @Description: HttpContext工具类
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-03
 */
public class HttpContextUtil {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
