package com.pierce.data.dao.statconf;

import com.pierce.data.vo.stat.StatConfCategoryVo;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.dao.hfstatconf
 * @Description: 海房平台配置
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-23
 */
public interface PlatformConfMapper {

    List<StatConfCategoryVo> getPlatformCategory();
}
