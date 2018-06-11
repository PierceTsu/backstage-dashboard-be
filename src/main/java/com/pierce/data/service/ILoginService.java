package com.pierce.data.service;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.vo.UserPermissionVo;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-05-31 17:34
 */
public interface ILoginService {
    ServerResponse<String> authLogin(String username, String password, String code);

    ServerResponse<UserPermissionVo> getInfo();

    ServerResponse logout();
}
