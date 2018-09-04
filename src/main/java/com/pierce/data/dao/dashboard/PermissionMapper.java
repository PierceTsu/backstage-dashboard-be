package com.pierce.data.dao.dashboard;

import com.pierce.data.pojo.dashboard.Permission;
import com.pierce.data.vo.sys.ResourceTreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface PermissionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    int updateByPrimaryKeySelective(Permission permission);

    Set<String> getAllPermissions();

    Set<String> getUserPermissionsById(Integer userId);

    List<String> getPermissionsByRouterId(Integer routerId);

    List<String> getPermissionsByRolesAndRouterId(@Param("roleIds") List<Integer> roleIds, @Param("routerId") Integer routerId);

    List<ResourceTreeVo> getAllResourceTree();

    List<Permission> selectByRouterId(Integer routerId);

    int countByRouterId(Integer routerId);
}