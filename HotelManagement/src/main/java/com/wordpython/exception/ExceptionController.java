package com.wordpython.exception;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wordpython
 * @Date 2019/9/25
 **/

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {Exception.class})
    public Object error(Exception ex, HttpSession httpSession){

        if(httpSession.getAttribute("isLogin")!=null){
            return "error/500isLogin";
        }
        return "error/500";
    }

}
