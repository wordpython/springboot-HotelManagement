package com.wordpython.controller;

import com.wordpython.entity.User;
import com.wordpython.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private LoginService loginService;

    /*判断注册用户名是否已存在*/
    @RequestMapping(value = "/checkName",method = RequestMethod.POST)
    public String checkName(@RequestBody User user){
        if(loginService.selectByUsername(user.getUsername())!=null){
            return "false";//说明用户名已被注册
        }
        return "true";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestBody User user){
        //服务端再次判断用户输入是否合法
        String regex="^[0-9a-zA-Z\u4E00-\u9FA5]{5,25}$";//用户名
        String reg="^[0-9a-zA-Z]{6,20}$";//密码
        if(user.getUsername().matches(regex)
                &&user.getPassword().matches(reg)
                &&user.getPassword().equals(user.getRePassword())) {
            //将数据插入数据库
            System.out.println(user);
            if(loginService.insertUser(user)>0) {//成功插入数据库
                //发送跳转地址
                return "true";
            }else {
                return "服务器错误,请刷新重新";
            }
        }else {
            return "用户名或密码错误";
        }
    }

    /*发送手机验证码*/
    @RequestMapping(value = "/sendPhone",method = RequestMethod.POST)
    public String sendPhone(@RequestBody User user) throws InterruptedException {
        System.out.println(user);
        //为了前端达到效果，
        Thread.currentThread().sleep(2000);
        return "true";
    }
}
