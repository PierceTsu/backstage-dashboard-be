package com.pierce.data.dao.dashboard;

import com.pierce.data.pojo.dashboard.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    List<RolePermission> selectAll();

    int updateByPrimaryKey(RolePermission record);

    int updateStatusByRoleId(Integer roleId);

    int deleteByRoleId(Integer id);

    List<Integer> selectPermissionsByRoleId(Integer id);

    int insertBatch(@Param("roleId") Integer roleId, @Param("permissionIds") int[] permissionIds);
}