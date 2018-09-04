package com.pierce.data.service.Impl;

import com.pierce.data.common.ResponseCode;
import com.pierce.data.common.ServerResponse;
import com.pierce.data.common.constant.SysConst;
import com.pierce.data.pojo.dashboard.User;
import com.pierce.data.service.ILoginService;
import com.pierce.data.service.IPermissionService;
import com.pierce.data.service.IRouterService;
import com.pierce.data.service.IUserService;
import com.pierce.data.vo.sys.RouterVo;
import com.pierce.data.vo.sys.UserRouterPermissionVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service.Impl
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
@Slf4j
@Service("iLoginService")
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRouterService routerService;

    @Autowired
    private IUserService userService;

    @Override
    public ServerResponse authLogin(String username, String password, String code) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(code)) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.ILLEGAL_ARGS.getCode(), ResponseCode.ILLEGAL_ARGS.getDesc());
        }

        Subject currentUser =  SecurityUtils.getSubject();
        //验证码校验
        Session session = currentUser.getSession();
        if (session.getAttribute(SysConst.SESSION_IMG_CODE) == null) {
            return ServerResponse.createByErrorMsg("请重新获取验证码");
        } else {
            if (!session.getAttribute(SysConst.SESSION_IMG_CODE).toString().equalsIgnoreCase(code)) {
                return ServerResponse.createByErrorMsg("验证码错误");
            }
        }

        User user = userService.findUserByUserName(username);
        if (user == null) {
            return ServerResponse.createByErrorMsg("用户不存在!");
        }

        if (user.getLocked()) {
            return ServerResponse.createByErrorMsg("账号未启用!");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            //清空验证码
            session.setAttribute(SysConst.SESSION_IMG_CODE, "");
            //返回用户的路由
            List<RouterVo> routers = routerService.getAuthRouterByUserId(user.getId());
            //返回用户的权限
            Set<String> permissions = permissionService.getAuthPermissionsByUserId(user.getId());
            //session中保存用户Id和权限信息
            session.setAttribute(SysConst.SESSION_USER_PERMISSION, permissions);
            session.setAttribute(SysConst.SESSION_USER_ID, user.getId());

            UserRouterPermissionVo userRouterPermissionVo = new UserRouterPermissionVo();
            userRouterPermissionVo.setRouters(routers);
            userRouterPermissionVo.setPermissions(permissions);
            userRouterPermissionVo.setUserName(username);
            userRouterPermissionVo.setNickname(user.getNickname());
            userRouterPermissionVo.setUserId(user.getId());

            return ServerResponse.createBySuccess(userRouterPermissionVo);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMsg("账号或者密码错误");
        }
    }

    @Override
    public ServerResponse logout() {
        try {
            SecurityUtils.getSubject().logout();
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }
}
