package com.pierce.data.vo.sys;

import javax.validation.constraints.NotNull;

/**
 * @Project : data
 * @Package Name : com.pierce.data.vo
 * @Description: 角色拥有的路由和资源权限
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-01
 */
public class RoleRouterPermissionVo {

    @NotNull(message = "角色id不能为空")
    private Integer roleId;
    private int[] routerIds;
    private int[] permissionIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public int[] getRouterIds() {
        return routerIds;
    }

    public void setRouterIds(int[] routerIds) {
        this.routerIds = routerIds;
    }

    public int[] getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(int[] permissionIds) {
        this.permissionIds = permissionIds;
    }
}
