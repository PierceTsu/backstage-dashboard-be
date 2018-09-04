package com.pierce.data.service;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.pojo.dashboard.Role;
import com.pierce.data.vo.PageInfoVo;
import com.pierce.data.vo.sys.RoleRouterPermissionVo;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
public interface IRoleService {
    ServerResponse<List<Role>> getAvailableRoles();

    ServerResponse<PageInfoVo> listRoleByPage(String name, int pageNum, int pageSize);

    ServerResponse<String> addRole(Role role);

    ServerResponse<String> updateRole(Role role);

    ServerResponse<String> removeRoleById(Integer id);

    ServerResponse<List<Integer>> getPermissionsByRoleId(Integer id);

    ServerResponse savePermissions(RoleRouterPermissionVo roleRouterPermissionVo);
}
