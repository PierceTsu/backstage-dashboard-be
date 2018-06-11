package com.pierce.data.service;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.pojo.Role;
import com.pierce.data.vo.RoleInfoVo;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11 14:33
 */
public interface IRoleService {
    ServerResponse<List<Role>> getAllRoles();

    ServerResponse<List<RoleInfoVo>> listRole();

    ServerResponse addRole(String roleName, String permissionIds);
}
