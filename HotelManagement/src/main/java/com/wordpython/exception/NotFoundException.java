package com.wordpython.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author wordpython
 * @Date 2019/9/25
 **/

@Controller
public class NotFoundException implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    /*
    * 业务逻辑层：如果是未登录用户，404页面提供“返回登录页面”按钮
    *           如果是已登录用户，404页面提供“返回主页面”按钮
    * 如果是已登录用户，则用户访问登录页面时自动重定向到主页或当前网站内页面
    *
    * */
    @RequestMapping(value = {"/error"})
    public Object error(HttpServletRequest request, HttpSession httpSession) {
        System.out.println("是否登录："+httpSession.getAttribute("isLogin"));
        if(httpSession.getAttribute("isLogin")!=null){
            return "error/404isLogin";
        }
        return "error/404";
    }
}
