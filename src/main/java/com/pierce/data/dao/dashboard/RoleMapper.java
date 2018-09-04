package com.pierce.data.dao.dashboard;

import com.pierce.data.pojo.dashboard.Role;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface RoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAvailableRoles();

    int updateByPrimaryKey(Role record);

    int updateByPrimaryKeySelective(Role role);

    int queryCountByRoleName(String roleName);

    List<Role> selectList(@Param("name") String name, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    int countRoleByName(@Param("name") String name);
}