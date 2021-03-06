package com.pierce.data.pojo.statconf;

import java.util.Date;

/**
 * @Project : data
 * @Package Name : com.pierce.data.pojo.hfstatconf
 * @Description: 渠道pojo
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-23
 */
public class ChannelConf {

    private Integer id;
    private String name;
    private String code;
    private Integer parentId;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
