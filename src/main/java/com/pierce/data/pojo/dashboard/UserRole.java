package com.pierce.data.pojo.dashboard;

import java.util.Date;

/**
 * @Project : data
 * @Package Name : com.pierce.data.pojo.dashboard
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-31
 */
public class UserRole {

    private Integer id;
    private Integer userId;
    private Integer roleId;
    private Date  createTime;
    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
