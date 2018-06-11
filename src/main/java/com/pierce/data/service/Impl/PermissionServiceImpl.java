package com.pierce.data.service.Impl;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.dao.PermissionMapper;
import com.pierce.data.service.IPermissionService;
import com.pierce.data.vo.MenuPermissionVo;
import com.pierce.data.vo.UserPermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service.Impl
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-01 16:15
 */
@Service("iPermissionService")
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserPermissionVo getUserPermissions(String username) {
        return permissionMapper.getUserPermissions(username);
    }

    @Override
    public Set<String> getAllMenu() {
        return permissionMapper.getAllMenu();
    }

    @Override
    public Set<String> getAllPermissionCodes() {
        return permissionMapper.getAllPermissionCodes();
    }

    @Override
    public ServerResponse<Set<MenuPermissionVo>> getAllMenuPermissions() {
        return ServerResponse.createBySuccess(permissionMapper.getAllMenuPermissions());
    }
}
