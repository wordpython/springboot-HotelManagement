package com.wordpython.shiro;

import com.wordpython.entity.User;
import com.wordpython.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author wordpython
 * @Date 2019/9/24
 **/

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;
    /*
    * 执行授权逻辑
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        //添加资源的授权字符串
        info.addStringPermission("user:search");

        /*到数据库查询当前登录用户的授权字符串*/
        //获取当前登录用户
//        Subject subject= SecurityUtils.getSubject();
//        User user=(User)subject.getPrincipal();
//        User dbUser=loginService.selectUser(user);
//        info.addStringPermission(dbUser.getPerms());


        return info;
    }

    /*
    * 执行认证逻辑
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        User user=loginService.selectByUsername(token.getUsername());
        if(user==null){
            //用户名不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }
        //2.判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
