package com.pierce.data.service.Impl;

import com.pierce.data.common.ResponseCode;
import com.pierce.data.common.ServerResponse;
import com.pierce.data.dao.UserMapper;
import com.pierce.data.pojo.User;
import com.pierce.data.service.IUserService;
import com.pierce.data.utils.StrUtil;
import com.pierce.data.vo.PageInfoVo;
import com.pierce.data.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project : data
 * @Package Name : com.pierce.data.service.Impl
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-05-31 22:42
 */
@Service("iUserService")
@Slf4j
public class UserServiceImpl implements IUserService {

    @Value("${salt}")
    private String salt;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserName(String username) {
        return userMapper.selectUserByUserName(username);
    }

    @Override
    public ServerResponse<PageInfoVo> listUser(int pageNum, int pageSize) {
        int pageStart = ((pageNum - 1) > 0 ? (pageNum - 1) : 0) * pageSize;
        log.info("pageStart: {}, pageSize: {}", pageStart, pageSize);
        List<UserInfoVo> userInfoVoList = userMapper.selectUserInfoList(pageStart, pageSize);
        PageInfoVo<UserInfoVo> pageInfoVo = new PageInfoVo<>();
        pageInfoVo.setDataList(userInfoVoList);
        pageInfoVo.setCount(userMapper.countUser());
        return ServerResponse.createBySuccess(pageInfoVo);
    }

    @Override
    public ServerResponse<String> addUser(User user) {
        int rowCount = userMapper.queryExistByUserName(user.getUsername());
        if (rowCount > 0) {
            return ServerResponse.createByErrorMsg("用户名已存在");
        }
        //密码加密存储
        user.setPassword(StrUtil.encryptedPwd(user.getPassword(), salt));
        user.setDeleteStatus("1");
        rowCount = userMapper.insert(user);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMsg("添加用户成功");
        }
        return ServerResponse.createByErrorMsg("添加失败");
    }

    @Override
    public ServerResponse updateUser(Integer userId, String nickname, String password, Integer roleId) {
        if (null == userId || null == roleId) {
            return ServerResponse.createByErrorCodeMsg(ResponseCode.ILLEGAL_ARGS.getCode(), ResponseCode.ILLEGAL_ARGS.getDesc());
        }
        //password校验
        if (null != password) {
            if (password.length() > 16 || password.length() < 6) {
                return ServerResponse.createByErrorMsg("密码必须是6到16位");
            }
            password = StrUtil.encryptedPwd(password, salt);
        }

        int rowCount = userMapper.updateByPrimaryKeySelective(userId, nickname, password, roleId);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMsg("更新成功");
        }
        return ServerResponse.createByErrorMsg("更新失败");
    }
}
