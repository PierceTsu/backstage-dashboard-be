package com.pierce.data.controller;

import com.pierce.data.common.ResponseCode;
import com.pierce.data.common.ServerResponse;
import com.pierce.data.pojo.dashboard.Role;
import com.pierce.data.service.IRoleService;
import com.pierce.data.vo.PageInfoVo;
import com.pierce.data.vo.sys.RoleRouterPermissionVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Project : data
 * @Package Name : com.pierce.data.controller
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 当前可用的角色列表
     * @return json
     */
    @GetMapping("/all")
    public ServerResponse getAvailableRoles() {
        return roleService.getAvailableRoles();
    }

    /**
     * 分页查询所有角色
     * @param name 角色名模糊查询
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return json
     */
    @RequiresPermissions("user:read")
    @PostMapping("/list")
    public ServerResponse<PageInfoVo> roleList(@RequestParam(required = false)String name,
                                               @RequestParam(value = "pageNum", defaultValue = "1")int pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "10")int pageSize) {
        return roleService.listRoleByPage(name, pageNum, pageSize);
    }

    /**
     * 添加角色权限
     * @return json
     */
    @RequiresPermissions("role:create")
    @PostMapping("/add")
    public ServerResponse<String> add(@Validated Role role) {
        return roleService.addRole(role);
    }

    /**
     * 修改角色权限
     * @return json
     */
    @RequiresPermissions("role:update")
    @PostMapping("/update")
    public ServerResponse<String> update(@Validated Role role) {
        if (null == role) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.ILLEGAL_ARGS.getCode(), ResponseCode.ILLEGAL_ARGS.getDesc());
        }
        if (role.getId() == null) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.ILLEGAL_ARGS.getCode(), ResponseCode.ILLEGAL_ARGS.getDesc());
        }
        return roleService.updateRole(role);
    }

    /**
     * 删除角色
     * @param id 角色id
     * @return
     */
    @RequiresPermissions("role:delete")
    @PostMapping("/remove")
    public ServerResponse<String> delete(@RequestParam() Integer id) {
        return roleService.removeRoleById(id);
    }

    /**
     * 根据角色id查询所有权限
     * @return
     */
    @PostMapping("/permissions")
    public ServerResponse getPermissionsByRoleId(@RequestParam() Integer id) {
        return roleService.getPermissionsByRoleId(id);
    }

    /**
     * 保存角色权限
     * @param roleRouterPermissionVo
     * @return json
     */
    @RequiresPermissions("role:update")
    @PostMapping("/savePermissions")
    public ServerResponse savePermissions(@Validated @RequestBody RoleRouterPermissionVo roleRouterPermissionVo) {
        return roleService.savePermissions(roleRouterPermissionVo);
    }
}
