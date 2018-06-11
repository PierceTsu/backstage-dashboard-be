package com.pierce.data.service;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.pojo.User;
import com.pierce.data.vo.PageInfoVo;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-05-31 22:42
 */
public interface IUserService {
    User findUserByUserName(String username);

    ServerResponse<PageInfoVo> listUser(int pageNum, int pageSize);

    ServerResponse<String> addUser(User user);

    ServerResponse updateUser(Integer userId, String nickname, String password, Integer roleId);

}
