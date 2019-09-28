package com.wordpython.controller;

import com.wordpython.dao.UserDao;
import com.wordpython.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/")
    public Object text(){
        User user=new User();
        user.setUsername("wordpython");
        System.out.println(userDao.selectUser(user));
        return ";ljsl;las";
    }
}
