package com.pierce.data.vo;

import java.io.Serializable;
import java.util.Set;

/**
 * @Project : data
 * @Package Name : com.pierce.data.vo
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-01 16:36
 */
public class UserPermissionVo implements Serializable {

    private Integer userId;
    private String nickname;
    private Integer roleId;
    private String roleName;
    private Set<String> menuList;
    private Set<String> permissionList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(Set<String> menuList) {
        this.menuList = menuList;
    }

    public Set<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(Set<String> permissionList) {
        this.permissionList = permissionList;
    }
}
