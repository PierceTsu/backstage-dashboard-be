package com.pierce.data.vo.sys;

import com.pierce.data.vo.sys.RouterVo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Project : data
 * @Package Name : com.pierce.data.vo
 * @Description: 登录后用户路由
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-07-20
 */
public class UserRouterPermissionVo implements Serializable {

    private Integer userId;
    private String userName;
    private String nickname;
    private List<RouterVo> routers;
    private Set<String> permissions;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<RouterVo> getRouters() {
        return routers;
    }

    public void setRouters(List<RouterVo> routers) {
        this.routers = routers;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
