package com.pierce.data.controller;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.config.captcha.YYYBCaptcha;
import com.pierce.data.dao.dashboard.RolePermissionMapper;
import com.pierce.data.dao.dashboard.RouterMapper;
import com.pierce.data.dao.dashboard.UserMapper;
import com.pierce.data.dao.dashboard.UserRoleMapper;
import com.pierce.data.pojo.dashboard.Router;
import com.pierce.data.service.*;
import com.pierce.data.vo.sys.RoleRouterPermissionVo;
import com.pierce.data.vo.sys.UserRolesVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project : data
 * @Package Name : com.pierce.data.controller
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
@Slf4j
//@RestController
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    @Autowired
    private IRouterService routerService;

    @Autowired
    private RouterMapper routerMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IResourceService resourceService;

    @Validated
    @PostMapping("/user")
    public ServerResponse user(@Validated @RequestBody UserRolesVo userRolesVo) {
        return ServerResponse.createBySuccess(userRolesVo);
    }

    @GetMapping("/{id}")
    public ServerResponse<UserRolesVo> getUserDetailById(@PathVariable("id") Integer id) {
        return userService.getUserDetailById(id);
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
        YYYBCaptcha yyybCaptcha = new YYYBCaptcha();
        String code = yyybCaptcha.generateCode();
        yyybCaptcha.generate(response, code);
    }

    @PostMapping("/role")
    public ServerResponse listRole(@Validated @RequestBody RoleRouterPermissionVo roleRouterPermissionVo) {
        return roleService.savePermissions(roleRouterPermissionVo);
    }

    @PostMapping("/router")
    public ServerResponse listRouter(@Validated Router router) {
        return routerService.updateRouter(router);
    }

}
