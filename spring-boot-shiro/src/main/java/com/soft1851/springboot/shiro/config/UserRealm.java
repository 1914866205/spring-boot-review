package com.soft1851.springboot.shiro.config;

import com.soft1851.springboot.shiro.pojo.User;
import com.soft1851.springboot.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/19 21:41
 * @Version 1.0
 **/
//自定义的 UserRealm
public class UserRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权doGetAuthorizationInfo");
        //授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("***" + info.toString() + "***");
//        info.addStringPermission("user:add");
//        info.addStringPermission("user:update");
        //拿到当前登录的这个对象
        // subject代表当前登录的用户
        Subject subject = SecurityUtils.getSubject();
//        System.out.println("***" + subject.toString() + "***");
//        System.out.println("***" + subject.getPrincipals() + "***");
//        System.out.println("***" + subject.getPrincipal().toString() + "***");
        User currentUser = (User) subject.getPrincipal();
        //设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());

//        return null;
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=》认证doGetAuthenticationInfo");
//        //用户名，密码。  数据库中取
//        //伪造数据
//        String name = "root";
//        String password = "123456";
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
//        if(!userToken.getUsername().equals(name)) {
//            //抛出异常   UnknownAccountException
//            return null;
//        }
        //连接真实的数据库
        User user = userService.queryUserByName(userToken.getUsername());
        if (user == null) {
            //没有这个人
            //抛出异常   UnknownAccountException
            return null;
        }
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);
        //密码认证  shiro做  加密
        //密码可以加密，如MD5加密，不可逆   MD5盐值加密
        //这里是这个输入的密码和数据库的密码相比较的
//        return new SimpleAuthenticationInfo(user.getName(), user.getPwd(), getName());
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}
