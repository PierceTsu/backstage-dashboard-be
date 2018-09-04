package com.pierce.data.vo.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.vo
 * @Description: 用户角色vo
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11
 */
@JsonInclude(JsonInclude.Include.NON_NULL)  //为空的对象, key不返回
public class UserRolesVo implements Serializable {

    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 16, message = "用户名需要4到32个字符")
    private String username;

//    @JsonIgnore
//    @NotBlank(message = "密码不能为空")
//    @Length(min = 6, max = 16, message = "密码长度6到16位")
    private String password;

    @NotBlank(message = "昵称不能为空")
    private String nickname;
    private Date createTime;
    private Date updateTime;
    private Boolean locked;
    private Integer creator;
    private List<Integer> roleIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}
