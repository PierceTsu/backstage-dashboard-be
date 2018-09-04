package com.pierce.data.service;

import com.pierce.data.pojo.statconf.AcquireActionConf;
import com.pierce.data.vo.stat.StatConfCategoryVo;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service
 * @Description: 统计配置service接口
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-23
 */
public interface IStatConfService {

    List<StatConfCategoryVo> getPlatformCategory();

    List<StatConfCategoryVo> getChannelCategory();

    List<AcquireActionConf> getAcquireAction();
}
