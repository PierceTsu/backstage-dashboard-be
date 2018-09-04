package com.pierce.data.pojo.dashboard;

import java.io.Serializable;
import java.util.Date;

/**
 * @Project : data
 * @Package Name : com.pierce.data.pojo.dashboard
 * @Description: 角色路由pojo
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-30
 */
public class RoleRouter implements Serializable {

    private Integer id;
    private Integer roleId;
    private Integer routerId;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRouterId() {
        return routerId;
    }

    public void setRouterId(Integer routerId) {
        this.routerId = routerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
