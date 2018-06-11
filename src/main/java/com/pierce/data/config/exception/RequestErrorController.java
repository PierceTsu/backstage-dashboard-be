package com.pierce.data.config.exception;

import com.pierce.data.common.ResponseCode;
import com.pierce.data.common.ServerResponse;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Project : data
 * @Package Name : com.pierce.data.config.exception
 * @Description: 404页面处理
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-05 16:06
 */
@Controller
public class RequestErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public ServerResponse handlerError() {
        return ServerResponse.createByErrorCodeMsg(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getDesc());
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
