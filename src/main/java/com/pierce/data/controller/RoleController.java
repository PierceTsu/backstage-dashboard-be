package com.pierce.data.controller;

import com.pierce.data.common.ResponseCode;
import com.pierce.data.common.ServerResponse;
import com.pierce.data.service.IPermissionService;
import com.pierce.data.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : data
 * @Package Name : com.pierce.data.controller
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11 17:50
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    /**
     * 角色列表
     * @return
     */
    @RequiresPermissions("role:list")
    @GetMapping("/list")
    public ServerResponse listRole() {
        return roleService.listRole();
    }

    /**
     * 查询所有菜单的权限, 给角色分配权限时调用
     * @return
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listAllPermission")
    public ServerResponse listAllPermissions() {
        return permissionService.getAllMenuPermissions();
    }

    /**
     * 添加角色权限, 多个权限id使用","拼接
     * @param roleName
     * @param permissionIds
     * @return
     */
    @RequiresPermissions("role:add")
    @PostMapping("/add")
    public ServerResponse add(String roleName, String permissionIds) {
        if (StringUtils.isBlank(roleName) || StringUtils.isBlank(permissionIds)) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.ILLEGAL_ARGS.getCode(), ResponseCode.ILLEGAL_ARGS.getDesc());
        }
        return roleService.addRole(roleName, permissionIds);
    }
}
