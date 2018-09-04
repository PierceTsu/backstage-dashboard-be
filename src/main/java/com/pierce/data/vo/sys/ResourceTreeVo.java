package com.pierce.data.vo.sys;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.vo
 * @Description: 资源树目录
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-07-31
 */
public class ResourceTreeVo {

    private Integer id;
    private String name;
    private List<ResourceTreeVo> children;

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

    public List<ResourceTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceTreeVo> children) {
        this.children = children;
    }
}
