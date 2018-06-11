package com.pierce.data.controller;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.pojo.Role;
import com.pierce.data.pojo.User;
import com.pierce.data.service.IRoleService;
import com.pierce.data.service.IUserService;
import com.pierce.data.vo.PageInfoVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.controller
 * @Description: 用户/角色/权限
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-04 13:53
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    /**
     * 获取 用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取用户列表", notes ="获取用户列表, 权限user:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true , dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, dataType = "int")
    })
    @RequiresPermissions("user:list")
    @PostMapping("/list")
    public ServerResponse<PageInfoVo> userList(@RequestParam(value = "pageNum", defaultValue = "1")int pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "10")int pageSize) {
        return userService.listUser(pageNum, pageSize);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequiresPermissions("user:add")
    @PostMapping("/add")
    public ServerResponse<String> addUser(@Validated User user) {
        return userService.addUser(user);
    }

    /**
     * 修改用户
     * @param userId
     * @param nickname
     * @param password
     * @param roleId
     * @return
     */
    @RequiresPermissions("user:update")
    @PostMapping("/update")
    public ServerResponse updateUser(Integer userId, String nickname, String password, Integer roleId) {
        return userService.updateUser(userId, nickname, password, roleId);
    }

    /**
     * 新增和修改用户时, 角色选择
     * @return
     */
    @RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
    @GetMapping("/getAllRoles")
    public ServerResponse<List<Role>> getAllRoles() {
        return roleService.getAllRoles();
    }
}
