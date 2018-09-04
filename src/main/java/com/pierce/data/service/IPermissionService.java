package com.pierce.data.service;

import java.util.List;
import java.util.Set;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
public interface IPermissionService {

    Set<String> getAuthPermissionsByUserId(Integer userId);

    List<String> getPermissionsByUserIdAndRouterId(Integer userId, Integer routerId);

    List<String> getUserPermissionsByRouterId(Integer routerId);
}
