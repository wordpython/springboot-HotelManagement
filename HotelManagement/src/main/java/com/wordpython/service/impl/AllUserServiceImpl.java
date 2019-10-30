package com.wordpython.service.impl;

import com.wordpython.dao.MapperUser;
import com.wordpython.entity.User;
import com.wordpython.service.AllUserService;
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
public class AllUserServiceImpl implements AllUserService {
    @Autowired
    private MapperUser mapperUser;
    @Override
    public User findByUsername(User user) {
        return mapperUser.findByUsername(user);
    }

    @Override
    public User selectUser(User user) {
        return mapperUser.selectUser(user);
    }

    @Override
    public int insertUser(User user) {
        user.setUser_id(new UUidUtil().getUUID());
        return mapperUser.insertUser(user);
    }

    @Override
    public int setStatus(String status) {
        return mapperUser.setStatus(status);
    }

    @Override
    public int setName(User user) {
        return mapperUser.setName(user);
    }

    @Override
    public int updatePassword(User user) {
        return mapperUser.updatePassword(user);
    }

    @Override
    public int updateUsername(String username) {
        return mapperUser.updateUsername(username);
    }

    @Override
    public int updateMail(String mail) {
        return mapperUser.updateMail(mail);
    }

    @Override
    public int updatePhone(String phone) {
        return mapperUser.updatePhone(phone);
    }

    @Override
    public int setBookingtimesAndLevel(User user) {
        return mapperUser.setBookingtimesAndLevel(user);
    }

    @Override
    public int deleteUser(User user) {
        return mapperUser.deleteUser(user);
    }

    @Override
    public List<User> selectPartUser(User user) {
        return mapperUser.selectPartUser(user);
    }

    @Override
    public int selectUserCount() {
        return mapperUser.selectUserCount();
    }
}
