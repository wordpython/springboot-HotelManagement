package com.wordpython.admin.controller;

import com.wordpython.admin.entity.Admin;
import com.wordpython.admin.entity.User;
import com.wordpython.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class ARegisterController {

    @Autowired
    private AdminService adminService;

    /*判断注册用户名是否已存在*/
    @RequestMapping(value = "/checkName",method = RequestMethod.POST)
    public String checkName(@RequestBody Admin admin){
        if(adminService.selectAdminByUsername(admin)!=null){
            return "false";//说明用户名已被注册
        }
        return "true";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestBody Admin admin){
        //服务端再次判断用户输入是否合法
        String regex="^[0-9a-zA-Z\u4E00-\u9FA5]{5,25}$";//用户名
        String reg="^[0-9a-zA-Z]{6,20}$";//密码
        if(admin.getUsername().matches(regex)
                &&admin.getPassword().matches(reg)
                &&admin.getPassword().equals(admin.getRePassword())) {
            //将数据插入数据库
            admin.setAdmin_id(UUID.randomUUID().toString());
            System.out.println(admin);
            if(adminService.insertAdmin(admin)>0) {//成功插入数据库
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
    public String sendPhone(@RequestBody Admin admin) throws InterruptedException {
        System.out.println(admin);
        //为了前端达到效果，
        Thread.currentThread().sleep(2000);
        return "true";
    }
}
