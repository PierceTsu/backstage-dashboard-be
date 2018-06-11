package com.pierce.data.controller;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.config.captcha.HnbCaptcha;
import com.pierce.data.service.ILoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project : data
 * @Package Name : com.pierce.data.controller
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-05-31 17:33
 */
@Api(value = "LoginController", description = "用户登录登出接口")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    /**
     * 登录时图形验证码
     * @param response
     */
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) {
        HnbCaptcha hnbCaptcha = new HnbCaptcha();
        String code = hnbCaptcha.generateCode();
        hnbCaptcha.generate(response, code);
    }

    /**
     * 登录校验
     * @param username
     * @param password
     * @param code
     * @return
     */
    @PostMapping("/auth")
    public ServerResponse<String> authLogin(String username, String password, String code) {
        return loginService.authLogin(username, password, code);
    }

    /**
     * 登录成功后获取用户的权限信息
     * @return
     */
    @PostMapping("/getInfo")
    public ServerResponse getInfo() {
        return loginService.getInfo();
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("/logout")
    public ServerResponse logout() {
        return loginService.logout();
    }
}
