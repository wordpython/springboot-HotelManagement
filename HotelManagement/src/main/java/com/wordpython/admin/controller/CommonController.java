package com.wordpython.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author wordpython
 * @Date 2019/10/18
 **/
@Controller
@RequestMapping("/common")
public class CommonController {
    @RequestMapping("")
    @ResponseBody
    public String common(HttpSession session){
        String src = session.getAttribute("src").toString();
        System.out.println("请求/common获得iframe链接:"+src);
        return src;
    }
}
