package com.pierce.data.service.Impl;

import com.pierce.data.common.constant.SysConst;
import com.pierce.data.common.ResponseCode;
import com.pierce.data.common.ServerResponse;
import com.pierce.data.dao.dashboard.UserMapper;
import com.pierce.data.dao.dashboard.UserRoleMapper;
import com.pierce.data.pojo.dashboard.User;
import com.pierce.data.service.IUserService;
import com.pierce.data.utils.StrUtil;
import com.pierce.data.vo.PageInfoVo;
import com.pierce.data.vo.sys.UserRolesVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service.Impl
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
@Service("iUserService")
@Slf4j
public class UserServiceImpl implements IUserService {

    @Value("${salt}")
    private String salt;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User findUserByUserName(String username) {
        return userMapper.selectUserByUserName(username);
    }

    @Override
    public ServerResponse<PageInfoVo> listUser(String name, int pageNum, int pageSize) {
        int pageStart = ((pageNum - 1) > 0 ? (pageNum - 1) : 0) * pageSize;
        log.info("pageStart: {}, pageSize: {}", pageStart, pageSize);
        List<User> userList =  userMapper.selectList(name, pageStart, pageSize);
        PageInfoVo<User> pageInfoVo = new PageInfoVo<>();
        pageInfoVo.setDataList(userList);
        pageInfoVo.setCount(userMapper.countUserByName(name));
        return ServerResponse.createBySuccess(pageInfoVo);
    }

    /**
     * 添加用户和角色
     * @param userRolesVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServerResponse<String> addUserAndRoles(UserRolesVo userRolesVo) {
        int rowCount = userMapper.queryExistByUserName(userRolesVo.getUsername());
        if (rowCount > 0) {
            return ServerResponse.createByErrorMsg("用户名已存在");
        }
        //password校验
        String password = userRolesVo.getPassword();

        if (null == password) {
            return ServerResponse.createByErrorMsg("密码不能为空");
        }

        if (password.length() > 16 || password.length() < 6) {
            return ServerResponse.createByErrorMsg("密码必须是6到16位");
        }
        password = StrUtil.encryptedPwd(password, salt);

        User user = new User();
        BeanUtils.copyProperties(userRolesVo, user);
        user.setPassword(password);
        user.setLocked(false);  //新增默认未锁定
        Session session = SecurityUtils.getSubject().getSession();
        Integer currentUserId = (Integer) session.getAttribute(SysConst.SESSION_USER_ID);
        user.setCreator(currentUserId);

        rowCount = userMapper.insert(user);
        if (rowCount > 0) {
            userRoleMapper.removeRoleByUserId(user.getId());
            userRoleMapper.addUserRole(user.getId(), userRolesVo.getRoleIds());
            return ServerResponse.createBySuccessMsg("添加用户成功");
        }
        return ServerResponse.createByErrorMsg("添加失败");
    }

    @Override
    public ServerResponse<UserRolesVo> getUserDetailById(Integer id) {
        if (null != id) {
            User user = userMapper.selectByPrimaryKey(id);
            if (null != user) {
                UserRolesVo userRolesVo = new UserRolesVo();
                BeanUtils.copyProperties(user, userRolesVo);
                userRolesVo.setPassword(null);
                List<Integer> roleIds = userRoleMapper.getUserRolesByUserId(id);
                userRolesVo.setRoleIds(roleIds);
                return ServerResponse.createBySuccess(userRolesVo);
            }
        }
        return ServerResponse.createByErrorCodeMsg(ResponseCode.ILLEGAL_ARGS.getCode(), ResponseCode.ILLEGAL_ARGS.getDesc());
    }

    @Override
    public ServerResponse changeUserPwdById(Integer userId, String pwd) {
        String password = StrUtil.encryptedPwd(pwd, salt);
        User user = new User();
        user.setId(userId);
        user.setPassword(password);
        int rowCount = userMapper.updateByPrimaryKeySelective(user);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("修改密码成功");
        }
        return ServerResponse.createByErrorMsg("修改密码失败");
    }

    /**
     * 更新用户和角色
     * 不需要密码校验
     * @param userRolesVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServerResponse updateUser(UserRolesVo userRolesVo) {
        if (null == userRolesVo) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.ILLEGAL_ARGS.getCode(), ResponseCode.ILLEGAL_ARGS.getDesc());
        }

        if (null == userRolesVo.getId()) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.ILLEGAL_ARGS.getCode(), ResponseCode.ILLEGAL_ARGS.getDesc());
        }

        User user = new User();
        BeanUtils.copyProperties(userRolesVo, user);

        Session session = SecurityUtils.getSubject().getSession();
        Integer currentUserId = (Integer) session.getAttribute(SysConst.SESSION_USER_ID);
        user.setCreator(currentUserId);

        int rowCount = userMapper.updateByPrimaryKeySelective(user);
        if (rowCount > 0) {
            userRoleMapper.removeRoleByUserId(user.getId());
            userRoleMapper.addUserRole(user.getId(), userRolesVo.getRoleIds());
            return ServerResponse.createBySuccessMsg("更新成功");

        }
        return ServerResponse.createByErrorMsg("更新失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServerResponse removeUserById(Integer id) {
        if (null == id) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.ILLEGAL_ARGS.getCode(), ResponseCode.ILLEGAL_ARGS.getDesc());
        }
        int rowCounts = userMapper.deleteByPrimaryKey(id);
        if (rowCounts > 0) {
            //删除用户角色
            userRoleMapper.removeRoleByUserId(id);
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }
}
