package com.soft1851.springboot.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/19 21:39
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean  连接前端  3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /*
         *  anno: 无需认证就可以访问
         *  authc: 必须认证了才能访问
         *  user: 必须拥有 记住我  功能才能访问
         *  perms: 拥有对某个资源的权限才能访问
         *  role: 拥有某个角色权限才能访问
         */
        //登录拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/user/add", "authc");
//        filterMap.put("/user/update", "authc");
        //授权   401 未授权
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");
        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录的请求
        bean.setLoginUrl("/toLogin");
        //设置未授权的请求
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    //DefaultWebSecurityManager   接管对象  2
    //通过@Qualifier制定方法名，把参数和下面的方法绑定
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm());
        return securityManager;
    }


    //创建 realm 对象,需要自定义  1
    //也可以 @Bean(name = "userRealm"),则上面就不用制定了
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }


    //整合ShiroDialect: 用来整合  shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
