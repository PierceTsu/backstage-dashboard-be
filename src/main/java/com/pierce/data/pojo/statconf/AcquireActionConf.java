package com.pierce.data.pojo.statconf;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @Project : data
 * @Package Name : com.pierce.data.pojo.hfstatconf
 * @Description: 获客pojo
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-23
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AcquireActionConf {

    private Integer id;
    private String name;
    private String code;
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

    @Override
    public String toString() {
        return "AcquireActionConf{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
