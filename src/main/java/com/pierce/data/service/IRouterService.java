package com.pierce.data.service;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.pojo.dashboard.Router;
import com.pierce.data.vo.PageInfoVo;
import com.pierce.data.vo.sys.RouterVo;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-07-17
 */
public interface IRouterService {

    List<RouterVo> getAllRouterNodes();

    List<RouterVo> getAuthRouterByUserId(int userId);

    ServerResponse<PageInfoVo> listRouterByPage(String name, int pageNum, int pageSize);

    ServerResponse removeById(Integer id);

    ServerResponse updateRouter(Router router);

    ServerResponse addRouter(Router router);

    List<Router> getAvailableRouterRoot();
}
