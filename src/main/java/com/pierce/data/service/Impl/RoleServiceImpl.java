package com.pierce.data.service.Impl;

import com.pierce.data.common.ResponseCode;
import com.pierce.data.common.ServerResponse;
import com.pierce.data.dao.dashboard.RoleMapper;
import com.pierce.data.dao.dashboard.RolePermissionMapper;
import com.pierce.data.dao.dashboard.RoleRouterMapper;
import com.pierce.data.dao.dashboard.UserRoleMapper;
import com.pierce.data.pojo.dashboard.Role;
import com.pierce.data.service.IRoleService;
import com.pierce.data.vo.PageInfoVo;
import com.pierce.data.vo.sys.RoleRouterPermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service.Impl
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
@Service("iRoleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private RoleRouterMapper roleRouterMapper;

    @Override
    public ServerResponse<List<Role>> getAvailableRoles() {
        return ServerResponse.createBySuccess(roleMapper.selectAvailableRoles());
    }

    @Override
    public ServerResponse<String> addRole(Role role) {
        if (null != role) {
            int rowCount = roleMapper.queryCountByRoleName(role.getRoleName());
            if (rowCount > 0) {
                return ServerResponse.createByErrorMsg("角色名已经存在");
            } else {
                int count = roleMapper.insert(role);
                if (count > 0) {
                    return ServerResponse.createBySuccess("添加角色成功");
                }
            }
        }
        return ServerResponse.createByErrorMsg("角色权限添加失败");
    }

    @Override
    public ServerResponse<String> updateRole(Role role) {
        int rowCount = roleMapper.updateByPrimaryKeySelective(role);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("修改角色成功");
        }
        return ServerResponse.createByErrorMsg("修改角色失败");
    }

    @Override
    public ServerResponse<PageInfoVo> listRoleByPage(String name, int pageNum, int pageSize) {
        int pageStart = ((pageNum - 1) > 0 ? (pageNum - 1) : 0) * pageSize;
        List<Role> roleList = roleMapper.selectList(name, pageStart, pageSize);
        PageInfoVo<Role> pageInfoVo = new PageInfoVo<>();
        pageInfoVo.setDataList(roleList);
        pageInfoVo.setCount(roleMapper.countRoleByName(name));
        return ServerResponse.createBySuccess(pageInfoVo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServerResponse<String> removeRoleById(Integer id) {
        // 查询该角色下的用户
        int count = userRoleMapper.countByRoleId(id);
        if (count > 0) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.DELETE_ROLE_NO_ALLOW.getCode(), ResponseCode.DELETE_ROLE_NO_ALLOW.getDesc());
        }
        roleMapper.deleteByPrimaryKey(id);
        // 删除角色权限
        rolePermissionMapper.deleteByRoleId(id);
        // 删除角色路由
        roleRouterMapper.deleteByPrimaryKey(id);
        return ServerResponse.createBySuccess("删除角色成功");
    }

    @Override
    public ServerResponse<List<Integer>> getPermissionsByRoleId(Integer id) {
        return ServerResponse.createBySuccess(rolePermissionMapper.selectPermissionsByRoleId(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServerResponse savePermissions(RoleRouterPermissionVo roleRouterPermissionVo) {
        if (null != roleRouterPermissionVo) {
            // 先删除旧路由权限再添加
            roleRouterMapper.deleteByRoleId(roleRouterPermissionVo.getRoleId());
            rolePermissionMapper.deleteByRoleId(roleRouterPermissionVo.getRoleId());

            roleRouterMapper.insertBatch(roleRouterPermissionVo.getRoleId(), roleRouterPermissionVo.getRouterIds());
            rolePermissionMapper.insertBatch(roleRouterPermissionVo.getRoleId(), roleRouterPermissionVo.getPermissionIds());
            return ServerResponse.createBySuccess("保存成功");
        }
        return ServerResponse.createByErrorMsg("保存失败");
    }
}
