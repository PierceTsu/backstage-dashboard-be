package com.pierce.data.vo;

import com.pierce.data.pojo.User;

import java.io.Serializable;
import java.util.Set;

/**
 * @Project : data
 * @Package Name : com.pierce.data.vo
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11 15:08
 */
public class RoleInfoVo implements Serializable {

    private Integer roleId;
    private String roleName;

    private Set<User> userList;

    private Set<MenuPermissionVo> menuList;

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

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }

    public Set<MenuPermissionVo> getMenuList() {
        return menuList;
    }

    public void setMenuList(Set<MenuPermissionVo> menuList) {
        this.menuList = menuList;
    }
}
