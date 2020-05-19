package com.soft1851.springboot.shiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/19 21:41
 * @Version 1.0
 **/
//自定义的 UserRealm
public class UserRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权doGetAuthorizationInfo");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=》认证doGetAuthenticationInfo");
        //用户名，密码。  数据库中取
        //伪造数据
        String name = "root";
        String password = "123456";
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        if(!userToken.getUsername().equals(name)) {
            //抛出异常   UnknownAccountException
            return null;
        }

        //密码认证  shiro做
        return new SimpleAuthenticationInfo("", password, "");
    }
}
