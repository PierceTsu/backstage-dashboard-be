package com.pierce.data.dao.dashboard;

import org.apache.ibatis.annotations.Param;

/**
 * @Project : data
 * @Package Name : com.pierce.data.dao.dashboard
 * @Description: 角色路由
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-31
 */
public interface RoleRouterMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByRoleId(Integer id);

    int insertBatch(@Param("roleId") Integer roleId, @Param("routerIds") int[] routerIds);

    int countByRouterId(Integer routeId);
}
