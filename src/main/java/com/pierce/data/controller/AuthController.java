package com.pierce.data.controller;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.config.captcha.YYYBCaptcha;
import com.pierce.data.service.ILoginService;
import com.pierce.data.service.IPermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.controller
 * @Description: 用户登录登出接口
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
@Api(value = "AuthController", description = "用户登录登出接口")
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private IPermissionService permissionService;

    @GetMapping()
    public String index() {
        return "index";
    }

    /**
     * 登录时图形验证码
     * @param response json
     */
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) {
        YYYBCaptcha yyybCaptcha = new YYYBCaptcha();
        String code = yyybCaptcha.generateCode();
        yyybCaptcha.generate(response, code);
    }

    /**
     * 登录校验
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @return json 登录成功后返回用户的路由和权限
     */
    @PostMapping("/login")
    @ResponseBody
    public ServerResponse login(String username, String password, String code) {
        return loginService.authLogin(username, password, code);
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    @ResponseBody
    public ServerResponse logout() {
        return loginService.logout();
    }

    @PostMapping("/permissionList")
    @ResponseBody
    public ServerResponse getUserPermissionsByRouterId(@RequestParam() Integer routerId) {

        List<String> permissions = permissionService.getUserPermissionsByRouterId(routerId);
        return ServerResponse.createBySuccess(permissions);
    }
}
