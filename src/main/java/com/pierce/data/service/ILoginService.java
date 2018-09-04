package com.pierce.data.service;

import com.pierce.data.common.ServerResponse;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
public interface ILoginService {

    ServerResponse authLogin(String username, String password, String code);

    ServerResponse logout();
}
