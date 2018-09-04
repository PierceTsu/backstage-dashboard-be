package com.pierce.data.dao.dashboard;

import com.pierce.data.pojo.dashboard.Router;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Project : data
 * @Package Name : com.pierce.data.dao.dashboard
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-31
 */
public interface RouterMapper {

    Set<Router> getRouterListByUserId(int userId);

    Set<Router> getAllRouters();

    List<Router> selectList(@Param("name") String name, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    int countByName(@Param("name") String name);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(Router router);

    int insert(Router router);

    List<Router> selectAvailableRouterRoot();
}
