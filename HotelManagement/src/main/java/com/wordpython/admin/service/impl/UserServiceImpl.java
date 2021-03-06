package com.wordpython.admin.service.impl;

import com.wordpython.admin.dao.UserMapper;
import com.wordpython.admin.entity.User;
import com.wordpython.admin.service.UserService;
import com.wordpython.utils.UUidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author wordpython
 * @Date 2019/10/18
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User selectUser(User user) {
        return userMapper.selectUser(user);
    }

    @Override
    public int insertUser(User user) {
        user.setUser_id(new UUidUtil().getUUID());
        return userMapper.insertUser(user);
    }

    @Override
    public int setStatus(String status) {
        return 0;
    }

    @Override
    public int setName(User user) {
        return 0;
    }

    @Override
    public int updatePassword(String password) {
        return 0;
    }

    @Override
    public int updateUsername(String username) {
        return 0;
    }

    @Override
    public int updateMail(String mail) {
        return 0;
    }

    @Override
    public int updatePhone(String phone) {
        return 0;
    }

    @Override
    public int setBookingtimesAndLevel(User user) {
        return 0;
    }

    @Override
    public int deleteUser(User user) {
        return 0;
    }

    @Override
    public List<User> selectPartUser(User user) {
        return null;
    }

    @Override
    public int selectUserCount() {
        return 0;
    }
}
