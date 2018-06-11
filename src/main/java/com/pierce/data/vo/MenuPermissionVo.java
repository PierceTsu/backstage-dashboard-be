package com.pierce.data.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pierce.data.pojo.Permission;

import java.io.Serializable;
import java.util.Set;

/**
 * @Project : data
 * @Package Name : com.pierce.data.vo
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11 15:32
 */
@JsonInclude(JsonInclude.Include.NON_NULL)  //为空的对象, key不返回
public class MenuPermissionVo implements Serializable {

    private String menuCode;
    private String menuName;
    private Set<Permission> permissionList;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Set<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(Set<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
