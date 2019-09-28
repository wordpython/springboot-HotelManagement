package com.wordpython.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public Object login(){
        return "common/login";
    }

    @RequestMapping(value = "/index.html",method = RequestMethod.GET)
    public Object index() {
        return "common/index";
    }
    @RequestMapping(value = "/mail.html",method = RequestMethod.GET)
    public Object mail() {
        return "common/mail";
    }
    @RequestMapping(value = "/register.html",method = RequestMethod.GET)
    public Object register() {
        return "common/register";
    }
    @RequestMapping(value = "/sno.html",method = RequestMethod.GET)
    public Object sno() {
        return "common/sno";
    }
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public Object search() {
        return "user/search";
    }
    @GetMapping(value = "/search.html")
    public Object getSearch(){
        return "user/search";
    }
    @GetMapping(value = "/check.html")
    public Object check(){
        return "common/check";
    }
    @GetMapping(value = "/wordpython_agreement.html")
    public Object wordpython_agreement(){
        return "common/wordpython_agreement";
    }
    @GetMapping(value = "/legal_agreement.html")
    public Object legal_agreement(){
        return "common/legal_agreement";
    }
    @GetMapping(value = "/test.html")
    public Object test(){
        return "test";
    }
}
