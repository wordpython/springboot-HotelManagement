package com.wordpython.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author wordpython
 * @Date 2019/10/9
 **/
@Controller
@RequestMapping("/admin")
public class AIndexController {
    @GetMapping(value = "/index.html")
    public Object co(HttpSession session){
        System.out.println("admin/index.html页面");
        session.setAttribute("src","/mgr");//mgr
        return "admin/index";
    }
    @RequestMapping("/system/console")
    @ResponseBody
    public String console() {
        System.out.println("/system/console");
        return "admin/index";
    }
    //头像

}
