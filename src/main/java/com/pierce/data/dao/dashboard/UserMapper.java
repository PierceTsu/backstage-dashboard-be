package com.pierce.data.dao.dashboard;

import com.pierce.data.pojo.dashboard.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectUserByUserName(String username);

    int queryExistByUserName(String username);

    int countUser();

    int updateByPrimaryKeySelective(User user);

    List<User> selectList(@Param("username")String username, @Param("pageStart")int pageStart, @Param("pageSize")int pageSize);

    int countUserByName(@Param("username")String username);
}