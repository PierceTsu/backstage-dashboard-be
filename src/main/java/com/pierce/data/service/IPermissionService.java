package com.pierce.data.service;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.vo.MenuPermissionVo;
import com.pierce.data.vo.UserPermissionVo;

import java.util.Set;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-01 16:14
 */
public interface IPermissionService {
    UserPermissionVo getUserPermissions(String username);

    Set<String> getAllMenu();

    Set<String> getAllPermissionCodes();

    ServerResponse<Set<MenuPermissionVo>> getAllMenuPermissions();
}
