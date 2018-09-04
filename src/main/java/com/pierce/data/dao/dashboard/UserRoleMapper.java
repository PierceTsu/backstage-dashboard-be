package com.pierce.data.dao.dashboard;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.dao.dashboard
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-31
 */
public interface UserRoleMapper {

    List<Integer> getUserRolesByUserId(Integer userId);

    int removeRoleByUserId(Integer id);

    int addUserRole(@Param("id") Integer id, @Param("roleIds") List<Integer> roleIds);

    int countByRoleId(Integer id);
}
