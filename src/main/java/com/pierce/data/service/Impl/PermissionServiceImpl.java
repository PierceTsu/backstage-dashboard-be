package com.pierce.data.service.Impl;

import com.pierce.data.common.constant.SysConst;
import com.pierce.data.dao.dashboard.PermissionMapper;
import com.pierce.data.dao.dashboard.UserRoleMapper;
import com.pierce.data.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service.Impl
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
@Slf4j
@Service("iPermissionService")
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Set<String> getAuthPermissionsByUserId(Integer userId) {
        //管理员角色拥有所有权限
        List<Integer> roleIds = userRoleMapper.getUserRolesByUserId(userId);
        Set<String> permissions = new HashSet<>();
        if (roleIds.contains(SysConst.ADMIN_ROLE)) {
            permissions = permissionMapper.getAllPermissions();
        } else {
            permissions = permissionMapper.getUserPermissionsById(userId);
        }
        return permissions;
    }

    @Override
    public List<String> getPermissionsByUserIdAndRouterId(Integer userId, Integer routerId) {
        List<Integer> roleIds = userRoleMapper.getUserRolesByUserId(userId);
        List<String> permissions = new ArrayList<>();
        if (!roleIds.isEmpty()) {
            if (roleIds.contains(SysConst.ADMIN_ROLE)) {
                permissions = permissionMapper.getPermissionsByRouterId(routerId);
            } else {
                permissions = permissionMapper.getPermissionsByRolesAndRouterId(roleIds, routerId);
            }
        }
        return permissions;
    }

    /**
     * 根据路由id获取当前用户的权限
     * @param routerId
     * @return
     */
    @Override
    public List<String> getUserPermissionsByRouterId(Integer routerId) {
        Session session = SecurityUtils.getSubject().getSession();
        Integer useId = (Integer) session.getAttribute(SysConst.SESSION_USER_ID);
        if (null == useId) {
            return new ArrayList<String>();
        }
        return getPermissionsByUserIdAndRouterId(useId, routerId);
    }
}
