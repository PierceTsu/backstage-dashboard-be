package com.pierce.data.controller;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.pojo.dashboard.Permission;
import com.pierce.data.service.IResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Project : data
 * @Package Name : com.pierce.data.controller
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-07-27
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @PostMapping("/getByRouterId")
    public ServerResponse getByRouterId(@RequestParam() Integer routerId) {
        return resourceService.getByRouterId(routerId);
    }

    @GetMapping("/tree")
    public ServerResponse getResourceTree() {
        return resourceService.getResourceTree();
    }

    /**
     * 添加资源权限
     * @param permission
     * @return json
     */
    @RequiresPermissions("resources:create")
    @PostMapping("/add")
    public ServerResponse addResource(Permission permission) {
        return resourceService.addPermissions(permission);
    }

    /**
     * 更新资源权限
     * @param permission
     * @return json
     */
    @RequiresPermissions("resources:update")
    @PostMapping("/update")
    public ServerResponse updateResource(Permission permission) {
        return resourceService.updatePermissions(permission);
    }

    /**
     * 删除资源权限
     * @param id
     * @return
     */
    @RequiresPermissions("resources:delete")
    @PostMapping("/remove")
    public ServerResponse removeResourceById(@RequestParam() Integer id) {
        return resourceService.removeById(id);
    }
}
