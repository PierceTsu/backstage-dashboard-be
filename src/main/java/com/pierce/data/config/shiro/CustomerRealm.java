package com.pierce.data.config.shiro;

import com.pierce.data.common.Const;
import com.pierce.data.pojo.User;
import com.pierce.data.service.IUserService;
import com.pierce.data.vo.UserPermissionVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


/**
 * @Project : data
 * @Package Name : com.pierce.data.config.shiro
 * @Description: 自定义Realm
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-05-31 22:26
 */
@Slf4j
public class CustomerRealm extends AuthorizingRealm {

    @Value("${salt}")
    private String salt;

    @Autowired
    private IUserService userService;

    //获取授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Session session = SecurityUtils.getSubject().getSession();
        UserPermissionVo userPermissionVo = (UserPermissionVo) session.getAttribute(Const.SESSION_USER_PERMISSION);
        if (null == userPermissionVo) {
            return null;
        }
        log.info("userPermission: {}", userPermissionVo.getPermissionList().toString());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(userPermissionVo.getPermissionList());
        return authorizationInfo;
    }

    //获取身份验证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findUserByUserName(token.getUsername());
        if (null == user) {
            throw new AccountException("用户不存在!");
        }
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);

        //保存session
        SecurityUtils.getSubject().getSession().setAttribute(Const.SESSION_USER_INFO, token.getUsername());
        return new SimpleAuthenticationInfo(user, user.getPassword(),credentialsSalt, getName());
    }
}
