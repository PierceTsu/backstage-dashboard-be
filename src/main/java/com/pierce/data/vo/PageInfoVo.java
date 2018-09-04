package com.pierce.data.vo;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.vo
 * @Description: 分页vo
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
public class PageInfoVo<T> {

    private int count;
    private List<T> dataList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
