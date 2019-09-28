package com.wordpython.shiro;

import com.wordpython.interceptor.KickoutSessionFilter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author wordpython
 * @Date 2019/9/24
 **/
@Configuration
public class ShiroConfig {
    /*
    * 创建ShiroFilterFactoryBean
    * */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //自定义拦截器
        HashMap<String, Filter> filterMap = new LinkedHashMap<String, Filter>();
        filterMap.put("kickoutSessionFilter", kickoutSessionFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //添加Shiro内置过滤器
        /*
        * Shiro 内置过滤器，可以实现权限相关的拦截器
        *   常用的过滤器：
        *       anon:无需认证（登录）可以访问
        *       authc:必须认证才可以访问
        *       user:如果使用remenberMe的功能可以直接访问
        *       perms:该资源必须得到资源权限才可以访问
        *       role:该资源必须得到角色权限才可以访问
        * */
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/search.html","authc");//第一个参数表示访问地址
        filterChainDefinitionMap.put("/search","authc");
        filterChainDefinitionMap.put("/allUser","authc");
        filterChainDefinitionMap.put("/allUser.html","authc");
//        filterMap.put("/check","anon");filterMap.put("/index","anon");

        /*授权过滤器要放在拦截所有的上面，否则无效*/

        //授权过滤器
        filterChainDefinitionMap.put("/search","perms[user:search]");

        //拦截所有
//        filterMap.put("/*","authc");

        //拦截未登录 跳转到指定页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");//参数表示访问地址：localhost:8080/login

        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /*
    * 创建DefaultWebSecurityManager
    * */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /*
    * 创建Realm
    * */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /*
    @描述：kickoutSessionFilter同一个用户多设备登录限制
    * KickoutSessionFilter配置
    * */
    @Bean(name="myFilter")
    public KickoutSessionFilter kickoutSessionFilter(){
        KickoutSessionFilter kickoutSessionFilter = new KickoutSessionFilter();
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        //这里我们还是用之前shiro使用的ehcache实现的cacheManager()缓存管理
        //也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
//        kickoutSessionFilter.setCacheManager(ehCacheManager());
        //用于根据会话ID，获取会话进行踢出操作的；
//        kickoutSessionFilter.setSessionManager(sessionManager());
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
        kickoutSessionFilter.setKickoutAfter(false);
        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        kickoutSessionFilter.setMaxSession(1);
        //被踢出后重定向到的地址；
        kickoutSessionFilter.setKickoutUrl("/logout");
        return kickoutSessionFilter;
    }
}
