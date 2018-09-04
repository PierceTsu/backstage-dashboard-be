package com.pierce.data.controller;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.pojo.dashboard.Router;
import com.pierce.data.service.IRouterService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Project : data
 * @Package Name : com.pierce.data.controller
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-01
 */
@RestController
@RequestMapping("/router")
public class RouterController {

    @Autowired
    private IRouterService routerService;

    /**
     * 获取所有的路由节点
     * @return json
     */
    @GetMapping("/tree")
    public ServerResponse getRouterTree() {
        return ServerResponse.createBySuccess(routerService.getAllRouterNodes());
    }

    /**
     * 获取所有的根节点
     * @return json
     */
    @GetMapping("/root")
    public ServerResponse getRouterRoot() {
        return ServerResponse.createBySuccess(routerService.getAvailableRouterRoot());
    }

    /**
     * 分页查询路由列表
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequiresPermissions("routers:read")
    @PostMapping("/list")
    public ServerResponse routerList(@RequestParam(required = false) String name,
                                                   @RequestParam(value = "pageNume", defaultValue = "1")int pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10")int pageSize) {
        return routerService.listRouterByPage(name, pageNum, pageSize);
    }

    /**
     * 新增路由
     * @param router
     * @return json
     */
    @RequiresPermissions("routers:create")
    @PostMapping("/add")
    public ServerResponse addRouter(@Validated Router router) {
        return routerService.addRouter(router);
    }

    /**
     * 更新路由信息
     * @param router
     * @return json
     */
    @RequiresPermissions("routers:update")
    @PostMapping("/update")
    public ServerResponse updateRouter(@Validated Router router) {
        return routerService.updateRouter(router);
    }

    /**
     * 删除路由信息
     * @param id 路由id
     * @return json
     */
    @RequiresPermissions("routers:delete")
    @PostMapping("/remove")
    public ServerResponse removeById(@RequestParam() Integer id) {
        return routerService.removeById(id);
    }
}
