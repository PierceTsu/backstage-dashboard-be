package com.pierce.data.service.Impl;

import com.pierce.data.common.constant.SysConst;
import com.pierce.data.common.ServerResponse;
import com.pierce.data.dao.dashboard.PermissionMapper;
import com.pierce.data.dao.dashboard.RoleRouterMapper;
import com.pierce.data.dao.dashboard.RouterMapper;
import com.pierce.data.dao.dashboard.UserRoleMapper;
import com.pierce.data.pojo.dashboard.Router;
import com.pierce.data.service.IRouterService;
import com.pierce.data.vo.PageInfoVo;
import com.pierce.data.vo.sys.RouterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service.Impl
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-07-17
 */
@Service("iRouterService")
public class RouterServiceImpl implements IRouterService {

    @Autowired
    private RouterMapper routerMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleRouterMapper roleRouterMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 获取用户授权的路由, 管理员为所有的路由
     * @param userId 用户id
     * @return
     */
    public List<RouterVo> getAuthRouterByUserId(int userId) {
        List<Integer> roles = userRoleMapper.getUserRolesByUserId(userId);
        Set<Router> routerList;
        if (roles.contains(SysConst.ADMIN_ROLE)) {
            routerList = routerMapper.getAllRouters();
        } else {
            routerList = routerMapper.getRouterListByUserId(userId);
        }
        return generateNode(routerList);
    }

    @Override
    public ServerResponse<PageInfoVo> listRouterByPage(String name, int pageNum, int pageSize) {
        int pageStart = ((pageNum - 1) > 0 ? (pageNum - 1) : 0) * pageSize;
        List<Router> routerList = routerMapper.selectList(name, pageStart, pageSize);
        PageInfoVo<Router> pageInfoVo = new PageInfoVo<>();
        pageInfoVo.setDataList(routerList);
        pageInfoVo.setCount(routerMapper.countByName(name));
        return ServerResponse.createBySuccess(pageInfoVo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServerResponse removeById(Integer id) {
        // 查询是否有角色还拥有该路由, 存在则提示不能删除
        int rowCount = roleRouterMapper.countByRouterId(id);
        if (rowCount > 0) {
            return ServerResponse.createByErrorMsg("该路由下还存在用户角色, 请先删除");
        }
        // 查询路由下是否还存在权限
        rowCount = permissionMapper.countByRouterId(id);
        if (rowCount > 0) {
            return ServerResponse.createByErrorMsg("该路由下还存在权限, 请先删除");
        }
        // 删除路由
        rowCount = routerMapper.deleteByPrimaryKey(id);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }

    @Override
    public ServerResponse updateRouter(Router router) {
        if (null != router) {
            if (router.getId() != null) {
                int rowCount = routerMapper.updateByPrimaryKey(router);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("修改成功");
                }
            }
        }
        return ServerResponse.createByErrorMsg("修改失败");
    }

    @Override
    public ServerResponse addRouter(Router router) {
        if (null != router) {
            int rowCount = routerMapper.insert(router);
            if (rowCount > 0) {
                return ServerResponse.createBySuccess("添加成功");
            }
        }
        return ServerResponse.createByErrorMsg("新增失败");
    }

    @Override
    public List<Router> getAvailableRouterRoot() {
        return routerMapper.selectAvailableRouterRoot();
    }

    /**
     * 获取所有的路由节点
     * @return
     */
    public List<RouterVo> getAllRouterNodes() {
        Set<Router> routerList = routerMapper.getAllRouters();
        return generateNode(routerList);
    }

    private List<RouterVo> generateNode(Set<Router> routerList) {
        //获取父节点
        List<Router> parentRouter = new ArrayList<>();
        for (Router router : routerList) {
            if (router.getParentId() == 0) {
                parentRouter.add(router);
            }
        }
        //根据父节点id查找子节点
        List<RouterVo> routerVoList = new ArrayList<>();
        for (Router pRouter : parentRouter) {

            RouterVo routerVo = new RouterVo();
            routerVo.setChildren(new ArrayList<>());
            int parentId = pRouter.getId();
            BeanUtils.copyProperties(pRouter, routerVo);

            for (Router router : routerList) {
                if (parentId == router.getParentId()) {
                    routerVo.getChildren().add(router);
                }
            }
            routerVoList.add(routerVo);
        }

        return routerVoList;
    }
}
