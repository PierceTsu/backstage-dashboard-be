package com.pierce.data.service.Impl;

import com.pierce.data.dao.statconf.AcquireActionConfMapper;
import com.pierce.data.dao.statconf.ChannelConfMapper;
import com.pierce.data.dao.statconf.PlatformConfMapper;
import com.pierce.data.pojo.statconf.AcquireActionConf;
import com.pierce.data.service.IStatConfService;
import com.pierce.data.vo.stat.StatConfCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service.Impl
 * @Description: 统计配置service实现类
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-23
 */
@Service("iStatConfService")
public class StatConfServiceImpl implements IStatConfService {

    @Autowired
    private PlatformConfMapper platformConfMapper;

    @Autowired
    private ChannelConfMapper channelConfMapper;

    @Autowired
    private AcquireActionConfMapper acquireActionConfMapper;

    @Override
    public List<StatConfCategoryVo> getPlatformCategory() {
        return platformConfMapper.getPlatformCategory();
    }

    @Override
    public List<StatConfCategoryVo> getChannelCategory() {
        return channelConfMapper.getChannelCategory();
    }

    @Override
    public List<AcquireActionConf> getAcquireAction() {
        return acquireActionConfMapper.selectAll();
    }
}
