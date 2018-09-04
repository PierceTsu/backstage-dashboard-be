package com.pierce.data.vo.stat;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.vo.stat
 * @Description: 统计分类通用vo
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-23
 */
public class StatConfCategoryVo {

    private Integer id;
    private String name;
    private List<StatConfCategoryVo> children;

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

    public List<StatConfCategoryVo> getChildren() {
        return children;
    }

    public void setChildren(List<StatConfCategoryVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "StatConfCategoryVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
