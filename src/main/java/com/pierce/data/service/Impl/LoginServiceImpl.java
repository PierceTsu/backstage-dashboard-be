package com.pierce.data.service.Impl;

import com.pierce.data.common.Const;
import com.pierce.data.common.ResponseCode;
import com.pierce.data.common.ServerResponse;
import com.pierce.data.service.ILoginService;
import com.pierce.data.service.IPermissionService;
import com.pierce.data.vo.UserPermissionVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service.Impl
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-05-31 17:34
 */
@Slf4j
@Service("iLoginService")
public class LoginServiceImpl  implements ILoginService {

    @Autowired
    private IPermissionService permissionService;

    @Override
    public ServerResponse<String> authLogin(String username, String password, String code) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(code)) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.ILLEGAL_ARGS.getCode(), ResponseCode.ILLEGAL_ARGS.getDesc());
        }

        Subject currentUser =  SecurityUtils.getSubject();
        //验证码校验
        Session session = currentUser.getSession();
        if (session.getAttribute(Const.SESSION_IMG_CODE) == null) {
            return ServerResponse.createByErrorMsg("请重新获取验证码");
        } else {
            if (!session.getAttribute(Const.SESSION_IMG_CODE).toString().equalsIgnoreCase(code)) {
                return ServerResponse.createByErrorMsg("验证码错误");
            }
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            //清空验证码
            session.setAttribute(Const.SESSION_IMG_CODE, "");
            return ServerResponse.createBySuccessMsg("登录成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMsg("登录失败");
        }
    }

    @Override
    public ServerResponse<UserPermissionVo> getInfo() {
        Session session = SecurityUtils.getSubject().getSession();
        String username = (String) session.getAttribute(Const.SESSION_USER_INFO);
        UserPermissionVo userPermissions = permissionService.getUserPermissions(username);
        //管理员roleId为1
        if (userPermissions != null && userPermissions.getRoleId() == 1) {
            //所有菜单和所有权限
            Set<String> menuList = permissionService.getAllMenu();
            Set<String> permissionList = permissionService.getAllPermissionCodes();
            userPermissions.setMenuList(menuList);
            userPermissions.setPermissionList(permissionList);
        }
        //session中保存用户的权限信息
        session.setAttribute(Const.SESSION_USER_PERMISSION, userPermissions);
        return ServerResponse.createBySuccess(userPermissions);
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
