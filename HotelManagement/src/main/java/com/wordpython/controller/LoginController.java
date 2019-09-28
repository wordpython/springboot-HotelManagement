package com.wordpython.controller;

import com.wordpython.entity.User;
import com.wordpython.service.LoginService;
import com.wordpython.utils.LoginD;
import com.wordpython.utils.RandomUtils;
import com.wordpython.utils.Send163Mail;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//import cn.stylefeng.roses.core.base.controller.BaseController;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController{
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String getLogin() {
        return "common/login";
    }

    /*
    * 点击登录执行的动作
    *
    * 业务：如果是已登录用户，访问该接口则拦截并重定向到当前页面
    * */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String postLogin(@RequestBody User user,HttpSession httpSession) {
        /*
        * 使用Shiro编写认证操作
        * */
        //1.获取Shiro编写认证操作
        Subject subject= SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        //3.执行登录方法
        try{
            subject.login(token);
            //登录成功
            httpSession.setAttribute("isLogin","true");
            return "user/search";
        }catch (UnknownAccountException e){
            //登录失败，用户名不存在
            return "用户名或密码错误";
        }catch (IncorrectCredentialsException e){
            //密码错误
            return "用户名或密码错误";
        }
    }

    /*学号登录*/
    @RequestMapping(value = "/snoLogin", method = RequestMethod.POST)
    @ResponseBody
    public String snoLogin(@RequestBody User user,HttpSession httpSession) {
        System.out.println(user);
        LoginD sendT = new LoginD();
        String username = "";
        try {
            username = sendT.sendLoginD(user.getAccount(), user.getPassword());//将用户登录信息进行验证,成功则返回 姓名，失败则返回null
        } catch (Exception e) {
            username = null;
        }
        System.out.println("sendsuccess:" + username);
        if (username != null) { //登陆成功
            user.setUsername(username);
            if (loginService.selectByUsername(username) == null) { // 数据库中没有该用户即为空，则插入
                //将数据插入数据库
                user.setPhone("17654323809");
                if (loginService.insertUser(user) > 0) { // 将用户信息插入数据库
                    System.out.println("用户信息已插入数据库");
                }
                System.out.println("数据库中无该用户！");
            }

            httpSession.setAttribute("isLogin","true");
            //发送跳转地址
            return "search";
        }
        return "学号或密码错误";
    }

    /*发送邮箱验证码*/
    @RequestMapping(value = "/sendMail")
    @ResponseBody
    public String sendMail(@RequestBody User user,HttpSession session) {
        Send163Mail ds = new Send163Mail();
        RandomUtils random = new RandomUtils();
        String code = random.getRandom();
        boolean kk = ds.sendMail(user.getMail(), "欢迎注册wordpython网站，验证码" + code + "如非本人操作，请检查账号安全", "注册邮件");
        if (kk) {
            session.setAttribute("code",code);
            System.out.println("邮箱及验证码为：" + user.getMail() + "  " + code);
            return "邮箱验证码发送成功，请留意查收";
        }
        return "邮箱无效，请重新输入";
    }

    /*邮箱登陆*/
    @RequestMapping(value = "/mailLogin")
    @ResponseBody
    public String mailLogin(@RequestBody User user,HttpSession httpSession) {
        System.out.println("mail:" + user.getMail());
        if(user.getCode().equals(httpSession.getAttribute("code"))){
            httpSession.removeAttribute("code");
            httpSession.setAttribute("isLogin","true");
            return "search";
        }
        return "验证码错误";
    }


    /*
    * 退出登录
    *
    * */
    @GetMapping(value = "/logout")
    public String logOut(HttpSession httpSession) {
        Subject subject= SecurityUtils.getSubject();
        subject.logout();
        return "common/login";
    }

    /*
    * 未授权提示页面
    * */
    @GetMapping("/noAuth")
    public String noAuth(){
        return "common/noAuth";
    }
}
