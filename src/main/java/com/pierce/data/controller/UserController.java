package com.pierce.data.controller;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.service.IUserService;
import com.pierce.data.vo.PageInfoVo;
import com.pierce.data.vo.sys.UserRolesVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Project : data
 * @Package Name : com.pierce.data.controller
 * @Description: 用户管理
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取用户列表", notes ="获取用户列表, 权限user:read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true , dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, dataType = "int")
    })
    @RequiresPermissions("user:read")
    @PostMapping("/list")
    public ServerResponse<PageInfoVo> userList(@RequestParam(required = false)String name,
                                               @RequestParam(value = "pageNum", defaultValue = "1")int pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "10")int pageSize) {
        return userService.listUser(name, pageNum, pageSize);
    }

    /**
     * 添加用户和角色
     * @param userRolesVo
     * @return
     */
    @RequiresPermissions("user:create")
    @PostMapping("/add")
    public ServerResponse<String> addUser(@Validated @RequestBody UserRolesVo userRolesVo) {
        return userService.addUserAndRoles(userRolesVo);
    }

    /**
     * 修改用户
     * @param userRolesVo
     * @return
     */
    @RequiresPermissions("user:update")
    @PostMapping("/update")
    public ServerResponse updateUser(@Validated @RequestBody UserRolesVo userRolesVo) {
        return userService.updateUser(userRolesVo);
    }

    /**
     * 修改用户密码
     * @param userId
     * @param pwd
     * @return
     */
    @RequiresPermissions(value = {"user:create", "user:update"}, logical = Logical.AND)
    @PostMapping("/changePwd")
    public ServerResponse changePwd(@RequestParam()Integer userId, @RequestParam String pwd) {
        return userService.changeUserPwdById(userId, pwd);
    }

    /**
     * 用户删除
     * @param id
     * @return
     */
    @RequiresPermissions("user:delete")
    @PostMapping("/remove")
    public ServerResponse removeUser(Integer id) {
        return userService.removeUserById(id);
    }

    /**
     * 根据用户id获取详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ServerResponse<UserRolesVo> getUserDetailById(@PathVariable("id") Integer id) {
        return userService.getUserDetailById(id);
    }
}
