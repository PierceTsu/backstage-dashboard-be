package com.pierce.data.dao.statconf;

import com.pierce.data.pojo.statconf.AcquireActionConf;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.dao.hfstatconf
 * @Description: 获客行为配置
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-23
 */
public interface AcquireActionConfMapper {

    List<AcquireActionConf> selectAll();
}
