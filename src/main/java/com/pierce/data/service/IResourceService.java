package com.pierce.data.service;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.pojo.dashboard.Permission;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service.Impl
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-07-31
 */
public interface IResourceService {

    ServerResponse getResourceTree();

    ServerResponse<List<Permission>> getByRouterId(Integer routerId);

    ServerResponse addPermissions(Permission permission);

    ServerResponse updatePermissions(Permission permission);

    ServerResponse removeById(Integer id);
}
