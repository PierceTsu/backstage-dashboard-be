package com.pierce.data.service.Impl;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.dao.RoleMapper;
import com.pierce.data.dao.RolePermissionMapper;
import com.pierce.data.pojo.Role;
import com.pierce.data.service.IRoleService;
import com.pierce.data.vo.RoleInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service.Impl
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11 14:33
 */
@Service("iRoleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public ServerResponse<List<Role>> getAllRoles() {
        return ServerResponse.createBySuccess(roleMapper.selectAll());
    }

    @Override
    public ServerResponse<List<RoleInfoVo>> listRole() {
        return ServerResponse.createBySuccess(roleMapper.getRoleList());
    }

    @Override
    public ServerResponse addRole(String roleName, String permissionIds) {
        //add sys_role
        int rowCount = roleMapper.queryByRoleName(roleName);
        if (rowCount > 0) {
            return ServerResponse.createByErrorMsg("角色名已存在");
        }
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDeleteStatus("1");
        int roleId = roleMapper.insert(role);
        //add sys_role_permission
        String[] pids = permissionIds.split(",");
        List<String> pidList = Arrays.asList(pids);
        int reusltCount = rolePermissionMapper.insertBatch(roleId, pidList);
        if (reusltCount > 0) {
            return ServerResponse.createBySuccessMsg("角色添加成功");
        }
        return ServerResponse.createByErrorMsg("角色添加失败");
    }
}
