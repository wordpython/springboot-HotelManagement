package com.wordpython.service;

import com.wordpython.entity.User;

import java.util.List;

public interface AllUserService {
    //查询昵称
    User findByUsername(String username);
    //添加验证码（60s有效）

    //登录，查询用户名和密码和登录状态，登录之前查询该用户是否已在其他地方登录
    User selectUser(User user);
    //注册，添加用户
    int insertUser(User user);
    //修改登录状态，用于单点登录
    int setStatus(String status);
    //实名，插入实名
    int setName(User user);
    //修改密码
    int updatePassword(String password);
    //修改昵称
    int updateUsername(String username);
    //修改邮箱
    int updateMail(String mail);
    //修改电话
    int updatePhone(String phone);
    //修改预订次数和等级
    int setBookingtimesAndLevel(User user);
    //注销用户
    int deleteUser(User user);
    //查询部分用户
    List<User> selectPartUser(User user);
    //查询用户总数
    int selectUserCount();
}
