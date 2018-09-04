package com.pierce.data.controller;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.service.IStatConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : data
 * @Package Name : com.pierce.data.controller
 * @Description: 配置类控制器
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-23
 */
@RestController
@RequestMapping("/conf")
public class ConfController {

    @Autowired
    private IStatConfService statConfService;

    /**
     * 平台分类接口
     * @return json
     */
    @GetMapping("/platform")
    public ServerResponse platform() {
        return ServerResponse.createBySuccess(statConfService.getPlatformCategory());
    }

    /**
     * 获客行为分类接口
     * @return json
     */
    @GetMapping("/acquireAction")
    public ServerResponse acquireAction() {
        return ServerResponse.createBySuccess(statConfService.getAcquireAction());
    }

    /**
     * 渠道接口
     * @return json
     */
    @GetMapping("/channel")
    public ServerResponse channel() {
        return ServerResponse.createBySuccess(statConfService.getChannelCategory());
    }

}
