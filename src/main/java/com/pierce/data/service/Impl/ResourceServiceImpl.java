package com.pierce.data.service.Impl;

import com.pierce.data.common.ServerResponse;
import com.pierce.data.dao.dashboard.PermissionMapper;
import com.pierce.data.pojo.dashboard.Permission;
import com.pierce.data.service.IResourceService;
import com.pierce.data.vo.sys.ResourceTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service.Impl
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-07-31
 */
@Service("iResourceService")
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 获取所有的资源树
     * @return
     */
    @Override
    public ServerResponse getResourceTree() {
        List<ResourceTreeVo> allResourceTree = permissionMapper.getAllResourceTree();
        return ServerResponse.createBySuccess(allResourceTree);
    }

    @Override
    public ServerResponse<List<Permission>> getByRouterId(Integer routerId) {
        return ServerResponse.createBySuccess(permissionMapper.selectByRouterId(routerId));
    }

    /**
     * 新增资源权限
     * @param permission
     * @return
     */
    @Override
    public ServerResponse addPermissions(Permission permission) {
        if (null == permission) {
            return ServerResponse.createByErrorMsg("参数不合法");
        }
        if (permission.getRouterId() == null || permission.getPermissionCode() == null || permission.getPermissionName() == null) {
            return ServerResponse.createByErrorMsg("参数不合法");
        }
        int rowCount = permissionMapper.insert(permission);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("添加成功");
        }
        return ServerResponse.createByErrorMsg("添加失败");
    }

    /**
     * 更新资源权限
     * @param permission
     * @return
     */
    @Override
    public ServerResponse updatePermissions(Permission permission) {
        if (null == permission) {
            return ServerResponse.createByErrorMsg("参数不合法");
        }
        if (permission.getId() == null) {
            return ServerResponse.createByErrorMsg("参数不合法");
        }
        int rowCount = permissionMapper.updateByPrimaryKeySelective(permission);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新成功");
        }
        return ServerResponse.createByErrorMsg("更新失败");
    }

    @Override
    public ServerResponse removeById(Integer id) {
        int rowCount = permissionMapper.deleteByPrimaryKey(id);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }
}
