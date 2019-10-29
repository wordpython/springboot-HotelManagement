package com.wordpython.admin.controller;

import com.wordpython.admin.entity.Admin;
import com.wordpython.admin.entity.UserMgr;
import com.wordpython.admin.service.AdminService;
import com.wordpython.admin.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author wordpython
 * @Date 2019/10/9
 **/
@Controller
@RequestMapping("/admin")
public class ALoginController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private HotelService hotelService;

    @GetMapping("/register.html")
    public String aRegister(){
        return "admin/register";
    }
    @GetMapping("/register")
    public String bRegister(){
        return "admin/register";
    }

    @GetMapping("/login.html")
    public String aLogin(){
        return "admin/login";
    }
    @GetMapping("/login")
    public String bLogin(){
        return "admin/login";
    }
    /*
    * 酒店店主登录
    * */
    @PostMapping("/loginForm")
    public Object loginForm(Admin admin, HttpSession session){
        System.out.println(admin);
        if(adminService.selectAdmin(admin)!=null){
            session.setAttribute("src","/mgr");//mgr
            String admin_id=adminService.selectAdmin(admin).getAdmin_id();
            session.setAttribute("admin_id",admin_id);
            //判断是否已经创建酒店，没有则跳转到创建酒店页面
            if(hotelService.selectHotelByAdmin_id(admin_id)==null){
                return "admin/add";
            }
            session.setAttribute("hotel_id",hotelService.selectHotelByAdmin_id(admin_id).getHotel_id());
            return "admin/index";
        }
        return "admin/login";
    }
}
