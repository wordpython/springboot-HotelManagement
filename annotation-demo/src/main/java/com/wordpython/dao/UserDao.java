package com.wordpython.dao;

import com.wordpython.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserDao {
    //查找用户名，密码
    @Select("select * from users where username=#{username}")
    public User selectUser(User user);
}
