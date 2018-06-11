package com.pierce.data.controller;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.config.captcha.HnbCaptcha;
import com.pierce.data.dao.RoleMapper;
import com.pierce.data.service.IUserService;
import com.pierce.data.vo.RoleInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.controller
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-05-31 23:50
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RoleMapper roleService;

    @GetMapping("/user")
    public ServerResponse test(@RequestParam(value = "pageNum", defaultValue = "1")int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10")int pageSize) {
        return userService.listUser(pageNum, pageSize);
    }

    @GetMapping("/log")
    public ServerResponse log() {
        log.trace("test log trace");
        log.debug("test log debug");
        log.info("test log info");
        log.warn("test log warn");
        log.error("test log error");
        return ServerResponse.createBySuccess();
    }

    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) {
        HnbCaptcha hnbCaptcha = new HnbCaptcha();
        String code = hnbCaptcha.generateCode();
        hnbCaptcha.generate(response, code);
    }

    @GetMapping("/listRole")
    public ServerResponse<List<RoleInfoVo>> listRole() {
        return ServerResponse.createBySuccess(roleService.getRoleList());
    }
}
