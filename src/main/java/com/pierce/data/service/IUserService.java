package com.pierce.data.service;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.pojo.dashboard.User;
import com.pierce.data.vo.PageInfoVo;
import com.pierce.data.vo.sys.UserRolesVo;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
public interface IUserService {
    User findUserByUserName(String username);

    ServerResponse<PageInfoVo> listUser(String name, int pageNum, int pageSize);

    ServerResponse updateUser(UserRolesVo userRolesVo);

    ServerResponse removeUserById(Integer id);

    ServerResponse<String> addUserAndRoles(UserRolesVo userRolesVo);

    ServerResponse<UserRolesVo> getUserDetailById(Integer id);

    ServerResponse changeUserPwdById(Integer userId, String pwd);
}
